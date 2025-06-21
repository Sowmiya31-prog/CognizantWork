public class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;
    
    // Constructor
    public Product(String productId, String productName, int quantity, double price) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        
        this.productId = productId.trim();
        this.productName = productName.trim();
        this.quantity = quantity;
        this.price = price;
    }
    
    // Getters
    public String getProductId() {
        return productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getPrice() {
        return price;
    }
    
    // Setters with validation
    public void setProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        this.productName = productName.trim();
    }
    
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
    
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }
    
    // Utility methods
    public double getTotalValue() {
        return quantity * price;
    }
    
    public boolean isInStock() {
        return quantity > 0;
    }
    
    @Override
    public String toString() {
        return String.format("Product{ID='%s', Name='%s', Quantity=%d, Price=%.2f, Value=%.2f}",
                productId, productName, quantity, price, getTotalValue());
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
}

