import java.util.ArrayList;

public class SmartReceipt {

    public static String formatProductName(String name) {
        name = name.trim();
        name = name.replace("_", " ");
        name = name.toUpperCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return name;
    }

    public static String generateReceipt(String customer, String store, ArrayList<String[]> cart, double subtotal, double tax, double total) {
        StringBuilder receipt = new StringBuilder();

        receipt.append("***********************************\n");
        receipt.append("\tSMART RECEIPT\n");
        receipt.append("***********************************\n");
        receipt.append("Customer:\t").append(customer.toUpperCase()).append("\n");
        receipt.append("Store:\t\t").append(store.trim()).append("\n");
        receipt.append("-----------------------------------\n");
        receipt.append("Item\t\tQty\tPrice\tTotal\n");

        for (String[] item : cart) {
            String product = item[0];
            double price = Double.parseDouble(item[1]);
            int qty = Integer.parseInt(item[2]);
            double lineTotal = price * qty;

            receipt.append(String.format("%-10s\t%d\t$%.2f\t$%.2f\n", product, qty, price, lineTotal));
        }

        receipt.append("-----------------------------------\n");
        receipt.append(String.format("Subtotal:\t\t\t$%.2f\n", subtotal));
        receipt.append(String.format("Tax (6%%):\t\t\t$%.2f\n", tax));
        receipt.append(String.format("Grand Total:\t\t$%.2f\n", total));
        receipt.append("***********************************\n");

        return receipt.toString();
    }
}

