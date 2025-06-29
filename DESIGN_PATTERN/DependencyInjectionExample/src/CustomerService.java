import java.util.List;

class CustomerService {
    
    private final CustomerRepository customerRepository;
    
    // Constructor Injection - The repository dependency is injected through constructor
    public CustomerService(CustomerRepository customerRepository) {
        if (customerRepository == null) {
            throw new IllegalArgumentException("CustomerRepository cannot be null");
        }
        this.customerRepository = customerRepository;
        System.out.println("Service: CustomerService initialized with repository: " 
                    + customerRepository.getClass().getSimpleName());
    }
    
    public Customer getCustomerById(String id) {
        System.out.println("Service: Processing request to get customer by ID: " + id);
        
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be null or empty");
        }
        
        Customer customer = customerRepository.findCustomerById(id);
        
        if (customer == null) {
            System.out.println("Service: No customer found with ID: " + id);
            return null;
        }
        
        System.out.println("Service: Successfully retrieved customer: " + customer.getName());
        return customer;
    }
    
    public List<Customer> getAllCustomers() {
        System.out.println("Service: Processing request to get all customers");
        List<Customer> customers = customerRepository.findAllCustomers();
        System.out.println("Service: Retrieved " + customers.size() + " customers");
        return customers;
    }
    
    public void createCustomer(String id, String name, String email, String phone) {
        System.out.println("Service: Processing request to create new customer: " + name);
        
        if (customerRepository.customerExists(id)) {
            throw new IllegalArgumentException("Customer with ID " + id + " already exists");
        }
        
        Customer newCustomer = new Customer(id, name, email, phone);
        customerRepository.saveCustomer(newCustomer);
        System.out.println("Service: Customer created successfully");
    }
    
    public void updateCustomer(String id, String name, String email, String phone) {
        System.out.println("Service: Processing request to update customer: " + id);
        
        Customer existingCustomer = customerRepository.findCustomerById(id);
        if (existingCustomer == null) {
            throw new IllegalArgumentException("Customer with ID " + id + " not found");
        }
        
        existingCustomer.setName(name);
        existingCustomer.setEmail(email);
        existingCustomer.setPhone(phone);
        
        customerRepository.updateCustomer(existingCustomer);
        System.out.println("Service: Customer updated successfully");
    }
    
    public void deleteCustomer(String id) {
        System.out.println("Service: Processing request to delete customer: " + id);
        
        if (!customerRepository.customerExists(id)) {
            throw new IllegalArgumentException("Customer with ID " + id + " not found");
        }
        
        customerRepository.deleteCustomer(id);
        System.out.println("Service: Customer deleted successfully");
    }
    
    public List<Customer> searchCustomersByName(String name) {
        System.out.println("Service: Processing search request for name: " + name);
        List<Customer> customers = customerRepository.findCustomersByName(name);
        System.out.println("Service: Found " + customers.size() + " customers matching the criteria");
        return customers;
    }
}
