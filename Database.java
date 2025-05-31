import java.util.*;

class Item {
    private String description, name;
    private double unitPrice;
    private int quantityAvailable;

    public Item(String description, String name, double unitPrice, int quantityAvailable) {
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantityAvailable = quantityAvailable;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int qty) {
        this.quantityAvailable = qty;
    }
}

class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name.trim();
        this.email = email.trim();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

public class Database {
    static ArrayList<Item> items = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static void addItem(Item itm) {
        items.add(itm);
        System.out.println("Item added successfully.");
    }

    static void removeItem(String item_name) {
        Iterator<Item> iter = items.iterator();
        boolean removed = false;
        while (iter.hasNext()) {
            Item e = iter.next();
            if (e.getName().equalsIgnoreCase(item_name.trim())) {
                iter.remove();
                System.out.println("Item removed: " + item_name);
                removed = true;
                break;
            }
        }
        if (!removed) System.out.println("Item not found.");
    }

    static void listAllItems() {
        System.out.println("Listing Items Available");
        for (Item e : items) {
            System.out.println("Item name: " + e.getName());
            System.out.println("Description: " + e.getDescription());
            System.out.println("Unit Price: " + e.getUnitPrice());
            System.out.println("Quantity Available: " + e.getQuantityAvailable());
            System.out.println("---------------------------");
        }
        if (items.isEmpty()) System.out.println("No items available.");
    }

   
    static void addCustomer(Customer c) {
        customers.add(c);
        System.out.println("Customer added successfully.");
    }

    
    static void listAllCustomers() {
        System.out.println("Listing Customers");
        for (Customer c : customers) {
            System.out.println("Name: " + c.getName());
            System.out.println("Email: " + c.getEmail());
            System.out.println("-------------------");
        }
        if (customers.isEmpty()) System.out.println("No customers available.");
    }

    static void printMenu() {
        System.out.println("------ Inventory Management ------");
        System.out.println("1. Add Item");
        System.out.println("2. Remove Item");
        System.out.println("3. Update Item Quantity");
        System.out.println("4. List all items");
        System.out.println("5. Add Customer");
        System.out.println("6. List all Customers");
        System.out.println("7. Search Item by Name");
        System.out.println("0. Exit");
        System.out.println("Choose an Option");
    }

    public static void select(int choice) {
        switch (choice) {
            case 1:
                System.out.print("Please enter Item Name   : ");
                String name = sc.nextLine().trim();

                System.out.print("Please enter Description  : ");
                String description = sc.nextLine().trim();

                double price = 0;
                int quantity = 0;
                try {
                    System.out.print("Please enter Unit Price   : ");
                    price = Double.parseDouble(sc.nextLine().trim());

                    System.out.print("Please enter Quantity Available  : ");
                    quantity = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format. Please enter valid numbers.");
                    break;
                }

                Item item = new Item(description, name, price, quantity);
                addItem(item);
                break;

            case 2:
                System.out.print("Please enter Item Name to remove: ");
                String itmName = sc.nextLine().trim();
                removeItem(itmName);
                break;

            case 3:
                System.out.print("Enter item name to update quantity: ");
                String itemToUpdate = sc.nextLine().trim();

                int newQty = 0;
                try {
                    System.out.print("Enter new quantity: ");
                    newQty = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format. Please enter a valid integer.");
                    break;
                }

                boolean updated = false;
                for (Item e : items) {
                    if (e.getName().equalsIgnoreCase(itemToUpdate)) {
                        e.setQuantityAvailable(newQty);
                        System.out.println("Quantity updated.");
                        updated = true;
                        break;
                    }
                }
                if (!updated) {
                    System.out.println("Item not found.");
                }
                break;

            case 4:
                listAllItems();
                break;

            case 5:
                System.out.print("Enter Customer Name: ");
                String custName = sc.nextLine().trim();

                System.out.print("Enter Customer Email: ");
                String custEmail = sc.nextLine().trim();

                if (custName.isEmpty() || custEmail.isEmpty()) {
                    System.out.println("Customer name and email cannot be empty.");
                    break;
                }

                Customer customer = new Customer(custName, custEmail);
                addCustomer(customer);
                break;

            case 6:
                listAllCustomers();
                break;

            case 7:
                System.out.print("Enter item name to search: ");
                String searchName = sc.nextLine().trim();
                boolean found = false;
                for (Item e : items) {
                    if (e.getName().equalsIgnoreCase(searchName)) {
                        System.out.println("Found Item:");
                        System.out.println("Name: " + e.getName());
                        System.out.println("Description: " + e.getDescription());
                        System.out.println("Unit Price: " + e.getUnitPrice());
                        System.out.println("Quantity Available: " + e.getQuantityAvailable());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Item not found.");
                }
                break;

            case 0:
                System.out.println("Exiting...");
                break;

            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public static void main(String[] arg) {
        int option;
        do {
            printMenu();
            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine();
                select(option);
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                option = -1;
            }
        } while (option != 0);
    }
}
