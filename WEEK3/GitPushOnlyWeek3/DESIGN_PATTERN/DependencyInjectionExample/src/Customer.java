import java.util.*;

// Customer.java - Model Class
class Customer {
    private String id;
    private String name;
    private String email;
    private String phone;
    
    public Customer(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    
    @Override
    public String toString() {
        return String.format("Customer{id='%s', name='%s', email='%s', phone='%s'}", 
                        id, name, email, phone);
    }
}