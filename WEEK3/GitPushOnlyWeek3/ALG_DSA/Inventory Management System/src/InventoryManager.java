import java.util.*;
import java.util.stream.Collectors;

/**
 * InventoryManager class handles all inventory operations
 * Uses HashMap for O(1) average case performance on primary operations
 */
public class InventoryManager {
    // Primary storage: HashMap for O(1) access by productId
    private HashMap<String, Product> inventory;
    
    // Secondary storage: ArrayList for ordered operations and iteration
    private ArrayList<Product> productList;
    
    public InventoryManager() {
        this.inventory = new HashMap<>();
        this.productList = new ArrayList<>();
    }
    
    /**
     * Add a new product to inventory
     * Time Complexity: O(1) average case
     * @param product Product to add
     * @return true if added successfully, false if product already exists
     */
    public boolean addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        
        String productId = product.getProductId();
        if (inventory.containsKey(productId)) {
            System.out.println("Product with ID " + productId + " already exists!");
            return false;
        }
        
        inventory.put(productId, product);
        productList.add(product);
        System.out.println("Product added successfully: " + product);
        return true;
    }
    
    /**
     * Update an existing product
     * Time Complexity: O(1) average case for HashMap, O(n) for ArrayList update
     * @param productId ID of product to update
     * @param newName New product name (null to keep current)
     * @param newQuantity New quantity (-1 to keep current)
     * @param newPrice New price (-1 to keep current)
     * @return true if updated successfully
     */
    public boolean updateProduct(String productId, String newName, int newQuantity, double newPrice) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Product with ID " + productId + " not found!");
            return false;
        }
        
        // Store old values for rollback if needed
        String oldName = product.getProductName();
        int oldQuantity = product.getQuantity();
        double oldPrice = product.getPrice();
        
        try {
            if (newName != null) {
                product.setProductName(newName);
            }
            if (newQuantity >= 0) {
                product.setQuantity(newQuantity);
            }
            if (newPrice >= 0) {
                product.setPrice(newPrice);
            }
            
            System.out.println("Product updated successfully: " + product);
            return true;
            
        } catch (IllegalArgumentException e) {
            // Rollback changes
            product.setProductName(oldName);
            product.setQuantity(oldQuantity);
            product.setPrice(oldPrice);
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Delete a product from inventory
     * Time Complexity: O(1) average case for HashMap, O(n) for ArrayList removal
     * @param productId ID of product to delete
     * @return true if deleted successfully
     */
    public boolean deleteProduct(String productId) {
        Product product = inventory.remove(productId);
        if (product == null) {
            System.out.println("Product with ID " + productId + " not found!");
            return false;
        }
        
        productList.remove(product);
        System.out.println("Product deleted successfully: " + product);
        return true;
    }
    
    /**
     * Get product by ID
     * Time Complexity: O(1) average case
     */
    public Product getProduct(String productId) {
        return inventory.get(productId);
    }
    
    /**
     * Search products by name (partial match)
     * Time Complexity: O(n)
     */
    public List<Product> searchProductsByName(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String lowerSearchTerm = searchTerm.toLowerCase().trim();
        return productList.stream()
                .filter(product -> product.getProductName().toLowerCase().contains(lowerSearchTerm))
                .collect(Collectors.toList());
    }
    
    /**
     * Get products with low stock (quantity <= threshold)
     * Time Complexity: O(n)
     */
    public List<Product> getLowStockProducts(int threshold) {
        return productList.stream()
                .filter(product -> product.getQuantity() <= threshold)
                .collect(Collectors.toList());
    }
    
    /**
     * Get total inventory value
     * Time Complexity: O(n)
     */
    public double getTotalInventoryValue() {
        return productList.stream()
                .mapToDouble(Product::getTotalValue)
                .sum();
    }
    
    /**
     * Get all products sorted by name
     * Time Complexity: O(n log n)
     */
    public List<Product> getAllProductsSortedByName() {
        return productList.stream()
                .sorted(Comparator.comparing(Product::getProductName))
                .collect(Collectors.toList());
    }
    
    /**
     * Get all products sorted by quantity (ascending)
     * Time Complexity: O(n log n)
     */
    public List<Product> getAllProductsSortedByQuantity() {
        return productList.stream()
                .sorted(Comparator.comparingInt(Product::getQuantity))
                .collect(Collectors.toList());
    }
    
    /**
     * Check if inventory is empty
     */
    public boolean isEmpty() {
        return inventory.isEmpty();
    }
    
    /**
     * Get inventory size
     */
    public int size() {
        return inventory.size();
    }
    
    /**
     * Display all products
     */
    public void displayAllProducts() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty!");
            return;
        }
        
        System.out.println("\n=== INVENTORY SUMMARY ===");
        System.out.println("Total Products: " + inventory.size());
        System.out.println("Total Value: $" + String.format("%.2f", getTotalInventoryValue()));
        System.out.println("\n=== PRODUCT LIST ===");
        
        productList.forEach(System.out::println);
    }
    
    /**
     * Bulk update quantity (useful for restocking)
     * Time Complexity: O(k) where k is number of updates
     */
    public void bulkUpdateQuantity(Map<String, Integer> updates) {
        int successCount = 0;
        int failCount = 0;
        
        for (Map.Entry<String, Integer> entry : updates.entrySet()) {
            String productId = entry.getKey();
            Integer newQuantity = entry.getValue();
            
            if (updateProduct(productId, null, newQuantity, -1)) {
                successCount++;
            } else {
                failCount++;
            }
        }
        
        System.out.println("Bulk update completed: " + successCount + " successful, " + failCount + " failed");
    }
}