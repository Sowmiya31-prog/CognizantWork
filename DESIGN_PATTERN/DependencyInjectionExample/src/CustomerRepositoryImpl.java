import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CustomerRepositoryImpl implements CustomerRepository {
    
    private Map<String, Customer> customerDatabase;
    
    public CustomerRepositoryImpl() {
        this.customerDatabase = new HashMap<>();
        initializeData();
    }
    
    private void initializeData() {
        // Initialize with some sample data
        customerDatabase.put("CUST001", new Customer("CUST001", "John Doe", "john.doe@email.com", "555-0101"));
        customerDatabase.put("CUST002", new Customer("CUST002", "Jane Smith", "jane.smith@email.com", "555-0102"));
        customerDatabase.put("CUST003", new Customer("CUST003", "Bob Johnson", "bob.johnson@email.com", "555-0103"));
        customerDatabase.put("CUST004", new Customer("CUST004", "Alice Brown", "alice.brown@email.com", "555-0104"));
    }
    
    @Override
    public Customer findCustomerById(String id) {
        System.out.println("Repository: Searching for customer with ID: " + id);
        Customer customer = customerDatabase.get(id);
        if (customer != null) {
            System.out.println("Repository: Customer found - " + customer.getName());
        } else {
            System.out.println("Repository: Customer not found with ID: " + id);
        }
        return customer;
    }
    
    @Override
    public List<Customer> findAllCustomers() {
        System.out.println("Repository: Retrieving all customers");
        return new ArrayList<>(customerDatabase.values());
    }
    
    @Override
    public void saveCustomer(Customer customer) {
        System.out.println("Repository: Saving customer - " + customer.getName());
        customerDatabase.put(customer.getId(), customer);
    }
    
    @Override
    public void updateCustomer(Customer customer) {
        System.out.println("Repository: Updating customer - " + customer.getName());
        if (customerDatabase.containsKey(customer.getId())) {
            customerDatabase.put(customer.getId(), customer);
        }
    }
    
    @Override
    public void deleteCustomer(String id) {
        System.out.println("Repository: Deleting customer with ID: " + id);
        customerDatabase.remove(id);
    }
    
    @Override
    public List<Customer> findCustomersByName(String name) {
        System.out.println("Repository: Searching for customers with name containing: " + name);
        return customerDatabase.values().stream()
                .filter(customer -> customer.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    @Override
    public boolean customerExists(String id) {
        return customerDatabase.containsKey(id);
    }
}
