/**
 * Product class for E-commerce Platform
 * Implements Comparable for binary search functionality
 */
public class Product implements Comparable<Product> {
    private String productId;
    private String productName;
    private String category;
    private double price;
    private int stock;
    private double rating;
    
    // Constructor
    public Product(String productId, String productName, String category, double price, int stock, double rating) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        
        this.productId = productId.trim();
        this.productName = productName.trim();
        this.category = category.trim();
        this.price = price;
        this.stock = stock;
        this.rating = rating;
    }
    
    // Getters
    public String getProductId() {
        return productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public String getCategory() {
        return category;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getStock() {
        return stock;
    }
    
    public double getRating() {
        return rating;
    }
    
    // Setters
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }
    
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.stock = stock;
    }
    
    public void setRating(double rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        this.rating = rating;
    }
    
    // Utility methods
    public boolean isInStock() {
        return stock > 0;
    }
    
    public boolean isHighRated() {
        return rating >= 4.0;
    }
    
    public String getPriceRange() {
        if (price < 50) return "Budget";
        else if (price < 200) return "Mid-range";
        else return "Premium";
    }
    
    // Comparable implementation for binary search (by productName)
    @Override
    public int compareTo(Product other) {
        return this.productName.compareToIgnoreCase(other.productName);
    }
    
    // Comparator methods for different sorting criteria
    public static int compareById(Product p1, Product p2) {
        return p1.productId.compareToIgnoreCase(p2.productId);
    }
    
    public static int compareByPrice(Product p1, Product p2) {
        return Double.compare(p1.price, p2.price);
    }
    
    public static int compareByRating(Product p1, Product p2) {
        return Double.compare(p2.rating, p1.rating); // Descending order
    }
    
    public static int compareByCategory(Product p1, Product p2) {
        return p1.category.compareToIgnoreCase(p2.category);
    }
    
    @Override
    public String toString() {
        return String.format("Product{ID='%s', Name='%s', Category='%s', Price=$%.2f, Stock=%d, Rating=%.1f}",
                productId, productName, category, price, stock, rating);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return productId.equals(product.productId);
    }
    
    @Override
    public int hashCode() {
        return productId.hashCode();
    }
    
    // Helper method for search result display
    public String toSearchResult() {
        return String.format("%-8s | %-25s | %-12s | $%-8.2f | Stock: %-3d | Rating: %.1f",
                productId, 
                productName.length() > 25 ? productName.substring(0, 22) + "..." : productName,
                category, price, stock, rating);
    }
}