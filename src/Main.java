import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<String[]> items = new ArrayList<>();

        items.add(new String[]{"Apples", "9.99"});
        items.add(new String[]{"Bannana", "5.99"});
        items.add(new String[]{"Steak", "19.99"});
        items.add(new String[]{"Toilet Paper", "11.23"});
        items.add(new String[]{"Paper Plates", "6.99"});
        items.add(new String[]{"Apple Watch", "299.99"});
        items.add(new String[]{"Laptop", "500.00"});
        items.add(new String[]{"Cereal", "6.99"});
        items.add(new String[]{"Chicken", "11.49"});
        items.add(new String[]{"Airpods", "199.99"});
        items.add(new String[]{"Soup", "4.99"});
        items.add(new String[]{"Paper plates", "8.57"});

        boolean shopping = true;

        while (shopping) {
            ArrayList<String[]> cart = new ArrayList<>();
            double subtotal = 0.0;

            System.out.println("\n=== Start a New Purchase ===");
            System.out.print("Customer Name: ");
            String customer = input.nextLine();

            System.out.print("Store Address: ");
            String store = input.nextLine();

            boolean addingItems = true;

            while (addingItems) {
                System.out.println("\nAvailable Items:");
                for (int i = 0; i < items.size(); i++) {
                    System.out.printf("%d. %s - $%s\n", i + 1, items.get(i)[0], items.get(i)[1]);
                }

                System.out.print("Enter item number to add (0 to finish): ");
                int choice = input.nextInt();
                input.nextLine();

                if (choice == 0) {
                    addingItems = false;
                    break;
                }

                if (choice < 1 || choice > items.size()) {
                    System.out.println("Invalid item number. Try again.");
                    continue;
                }

                String[] selected = items.get(choice - 1);
                String name = SmartReceipt.formatProductName(selected[0]);
                double price = Double.parseDouble(selected[1]);

                System.out.print("Quantity: ");
                int qty = input.nextInt();
                input.nextLine();

                double itemTotal = price * qty;
                if (subtotal + itemTotal > 500) {
                    double over = (subtotal + itemTotal) - 500;
                    System.out.print("You're over the $500 limit. Please remove something.\n");
                }

                subtotal += itemTotal;
                cart.add(new String[]{name, String.valueOf(price), String.valueOf(qty)});
                System.out.println(name + " x" + qty + " added.");
            }

            double tax = subtotal * 0.06;
            double grandTotal = subtotal + tax;

            String receipt = SmartReceipt.generateReceipt(customer, store, cart, subtotal, tax, grandTotal);
            System.out.println("\n" + receipt);

            System.out.print("Make another purchase? (yes/no): ");
            String again = input.nextLine();
            if (!again.toLowerCase().startsWith("y")) {
                shopping = false;
                System.out.println("Thanks for shopping with us!");
            }
        }
    }
}