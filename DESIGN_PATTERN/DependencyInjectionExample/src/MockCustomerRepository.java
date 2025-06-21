import java.util.Arrays;
import java.util.List;

class MockCustomerRepository implements CustomerRepository {
    
    @Override
    public Customer findCustomerById(String id) {
        System.out.println("MockRepository: Simulating customer lookup for ID: " + id);
        // Return a mock customer for testing
        return new Customer(id, "Mock Customer", "mock@test.com", "555-MOCK");
    }
    
    @Override
    public List<Customer> findAllCustomers() {
        System.out.println("MockRepository: Returning mock customer list");
        return Arrays.asList(
            new Customer("MOCK001", "Mock Customer 1", "mock1@test.com", "555-0001"),
            new Customer("MOCK002", "Mock Customer 2", "mock2@test.com", "555-0002")
        );
    }
    
    @Override
    public void saveCustomer(Customer customer) {
        System.out.println("MockRepository: Mock save operation for " + customer.getName());
    }
    
    @Override
    public void updateCustomer(Customer customer) {
        System.out.println("MockRepository: Mock update operation for " + customer.getName());
    }
    
    @Override
    public void deleteCustomer(String id) {
        System.out.println("MockRepository: Mock delete operation for ID: " + id);
    }
    
    @Override
    public List<Customer> findCustomersByName(String name) {
        System.out.println("MockRepository: Mock search by name: " + name);
        return Arrays.asList(new Customer("MOCK003", "Mock " + name, "mock@test.com", "555-0003"));
    }
    
    @Override
    public boolean customerExists(String id) {
        System.out.println("MockRepository: Mock existence check for ID: " + id);
        return true; // Always return true for mock
    }
}
