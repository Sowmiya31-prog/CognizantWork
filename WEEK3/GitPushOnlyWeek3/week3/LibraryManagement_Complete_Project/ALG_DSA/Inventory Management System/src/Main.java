import java.util.*;

public class Main {
    private static InventoryManager inventoryManager;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        inventoryManager = new InventoryManager();
        scanner = new Scanner(System.in);
        
        // Add some sample data
        initializeSampleData();
        
        // Start interactive menu
        showMainMenu();
    }
    
    private static void initializeSampleData() {
        System.out.println("Initializing sample data...");
        
        Product[] sampleProducts = {
            new Product("P001", "Laptop", 10, 999.99),
            new Product("P002", "Mouse", 50, 29.99),
            new Product("P003", "Keyboard", 30, 79.99),
            new Product("P004", "Monitor", 15, 299.99),
            new Product("P005", "Webcam", 8, 89.99),
            new Product("P006", "Headphones", 25, 149.99),
            new Product("P007", "USB Cable", 100, 12.99),
            new Product("P008", "External Hard Drive", 5, 129.99),
            new Product("P009", "Wireless Router", 12, 79.99),
            new Product("P010", "Tablet", 7, 349.99)
        };
        
        for (Product product : sampleProducts) {
            inventoryManager.addProduct(product);
        }
        
        System.out.println("Sample data initialized!\n");
    }
    
    /**
     * Display main menu and handle user input
     */
    private static void showMainMenu() {
        while (true) {
            System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
            System.out.println("1. Display All Products");
            System.out.println("2. Add New Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Search Product by ID");
            System.out.println("6. Search Products by Name");
            System.out.println("7. View Low Stock Products");
            System.out.println("8. View Products Sorted by Name");
            System.out.println("9. View Products Sorted by Quantity");
            System.out.println("10. View Inventory Statistics");
            System.out.println("11. Bulk Update Quantities");
            System.out.println("12. Performance Test");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    inventoryManager.displayAllProducts();
                    break;
                case 2:
                    addNewProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    searchProductById();
                    break;
                case 6:
                    searchProductsByName();
                    break;
                case 7:
                    viewLowStockProducts();
                    break;
                case 8:
                    viewProductsSortedByName();
                    break;
                case 9:
                    viewProductsSortedByQuantity();
                    break;
                case 10:
                    viewInventoryStatistics();
                    break;
                case 11:
                    bulkUpdateQuantities();
                    break;
                case 12:
                    performanceTest();
                    break;
                case 0:
                    System.out.println("Thank you for using Inventory Management System!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    private static void addNewProduct() {
        System.out.println("\n--- Add New Product ---");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine().trim();
        
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter Quantity: ");
        int quantity = getIntInput();
        
        System.out.print("Enter Price: ");
        double price = getDoubleInput();
        
        try {
            Product product = new Product(id, name, quantity, price);
            inventoryManager.addProduct(product);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void updateProduct() {
        System.out.println("\n--- Update Product ---");
        System.out.print("Enter Product ID to update: ");
        String id = scanner.nextLine().trim();
        
        System.out.print("Enter new name (or press Enter to keep current): ");
        String newName = scanner.nextLine().trim();
        if (newName.isEmpty()) newName = null;
        
        System.out.print("Enter new quantity (or -1 to keep current): ");
        int newQuantity = getIntInput();
        
        System.out.print("Enter new price (or -1 to keep current): ");
        double newPrice = getDoubleInput();
        
        inventoryManager.updateProduct(id, newName, newQuantity, newPrice);
    }
    
    private static void deleteProduct() {
        System.out.println("\n--- Delete Product ---");
        System.out.print("Enter Product ID to delete: ");
        String id = scanner.nextLine().trim();
        
        inventoryManager.deleteProduct(id);
    }
    
    private static void searchProductById() {
        System.out.println("\n--- Search Product by ID ---");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine().trim();
        
        Product product = inventoryManager.getProduct(id);
        if (product != null) {
            System.out.println("Product found: " + product);
        } else {
            System.out.println("Product not found!");
        }
    }
    
    private static void searchProductsByName() {
        System.out.println("\n--- Search Products by Name ---");
        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine().trim();
        
        List<Product> results = inventoryManager.searchProductsByName(searchTerm);
        if (results.isEmpty()) {
            System.out.println("No products found matching: " + searchTerm);
        } else {
            System.out.println("Found " + results.size() + " product(s):");
            results.forEach(System.out::println);
        }
    }
    
    private static void viewLowStockProducts() {
        System.out.println("\n--- Low Stock Products ---");
        System.out.print("Enter stock threshold: ");
        int threshold = getIntInput();
        
        List<Product> lowStockProducts = inventoryManager.getLowStockProducts(threshold);
        if (lowStockProducts.isEmpty()) {
            System.out.println("No products with stock <= " + threshold);
        } else {
            System.out.println("Products with stock <= " + threshold + ":");
            lowStockProducts.forEach(System.out::println);
        }
    }
    
    private static void viewProductsSortedByName() {
        System.out.println("\n--- Products Sorted by Name ---");
        List<Product> sortedProducts = inventoryManager.getAllProductsSortedByName();
        sortedProducts.forEach(System.out::println);
    }
    
    private static void viewProductsSortedByQuantity() {
        System.out.println("\n--- Products Sorted by Quantity ---");
        List<Product> sortedProducts = inventoryManager.getAllProductsSortedByQuantity();
        sortedProducts.forEach(System.out::println);
    }
    
    private static void viewInventoryStatistics() {
        System.out.println("\n--- Inventory Statistics ---");
        System.out.println("Total Products: " + inventoryManager.size());
        System.out.println("Total Inventory Value: $" + String.format("%.2f", inventoryManager.getTotalInventoryValue()));
        
        List<Product> lowStock = inventoryManager.getLowStockProducts(10);
        System.out.println("Products with stock <= 10: " + lowStock.size());
        
        if (!lowStock.isEmpty()) {
            System.out.println("Low stock products:");
            lowStock.forEach(p -> System.out.println("  " + p.getProductName() + " (Qty: " + p.getQuantity() + ")"));
        }
    }
    
    private static void bulkUpdateQuantities() {
        System.out.println("\n--- Bulk Update Quantities ---");
        Map<String, Integer> updates = new HashMap<>();
        
        System.out.println("Enter product updates (enter 'done' to finish):");
        while (true) {
            System.out.print("Product ID (or 'done'): ");
            String id = scanner.nextLine().trim();
            if (id.equalsIgnoreCase("done")) break;
            
            System.out.print("New quantity: ");
            int quantity = getIntInput();
            
            updates.put(id, quantity);
        }
        
        if (!updates.isEmpty()) {
            inventoryManager.bulkUpdateQuantity(updates);
        }
    }
    
    private static void performanceTest() {
        System.out.println("\n--- Performance Test ---");
        System.out.println("Testing operations on current inventory...");
        
        long startTime, endTime;
        
        // Test search performance
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            inventoryManager.getProduct("P001");  // HashMap lookup
        }
        endTime = System.nanoTime();
        System.out.println("1000 ID lookups took: " + (endTime - startTime) / 1_000_000.0 + " ms");
        
        // Test name search performance
        startTime = System.nanoTime();
        inventoryManager.searchProductsByName("Laptop");
        endTime = System.nanoTime();
        System.out.println("Name search took: " + (endTime - startTime) / 1_000_000.0 + " ms");
        
        // Test sorting performance
        startTime = System.nanoTime();
        inventoryManager.getAllProductsSortedByName();
        endTime = System.nanoTime();
        System.out.println("Sorting by name took: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }
    
    // Utility methods for input handling
    private static int getIntInput() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    private static double getDoubleInput() {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}