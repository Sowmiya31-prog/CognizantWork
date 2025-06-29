import java.util.List;

public class DependencyInjectionExample {
    
    public static void main(String[] args) {
        System.out.println("=== Dependency Injection Example ===\n");
        
        // Demonstrate dependency injection with different implementations
        demonstrateWithRealRepository();
        System.out.println("\n" + "=".repeat(50) + "\n");
        demonstrateWithMockRepository();
        System.out.println("\n" + "=".repeat(50) + "\n");
        demonstrateBenefitsOfDI();
    }
    
    private static void demonstrateWithRealRepository() {
        System.out.println("--- Using Real Repository Implementation ---");
        
        // Step 1: Create the repository implementation
        CustomerRepository repository = new CustomerRepositoryImpl();
        
        // Step 2: Inject the repository into the service (Constructor Injection)
        CustomerService customerService = new CustomerService(repository);
        
        // Step 3: Use the service to perform operations
        System.out.println("\n1. Finding customer by ID:");
        Customer customer = customerService.getCustomerById("CUST001");
        if (customer != null) {
            System.out.println("Found: " + customer);
        }
        
        System.out.println("\n2. Finding non-existent customer:");
        Customer nonExistent = customerService.getCustomerById("CUST999");
        
        System.out.println("\n3. Getting all customers:");
        List<Customer> allCustomers = customerService.getAllCustomers();
        allCustomers.forEach(c -> System.out.println("- " + c.getName()));
        
        System.out.println("\n4. Creating new customer:");
        try {
            customerService.createCustomer("CUST005", "Charlie Wilson", "charlie@email.com", "555-0105");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n5. Searching customers by name:");
        List<Customer> searchResults = customerService.searchCustomersByName("John");
        searchResults.forEach(c -> System.out.println("Search result: " + c.getName()));
    }
    
    private static void demonstrateWithMockRepository() {
        System.out.println("--- Using Mock Repository Implementation ---");
        
        // Step 1: Create a mock repository for testing
        CustomerRepository mockRepository = new MockCustomerRepository();
        
        // Step 2: Inject the mock repository into the service
        CustomerService customerService = new CustomerService(mockRepository);
        
        // Step 3: Use the service with mock data
        System.out.println("\n1. Finding customer with mock repository:");
        Customer mockCustomer = customerService.getCustomerById("TEST001");
        if (mockCustomer != null) {
            System.out.println("Mock customer: " + mockCustomer);
        }
        
        System.out.println("\n2. Getting all mock customers:");
        List<Customer> mockCustomers = customerService.getAllCustomers();
        mockCustomers.forEach(c -> System.out.println("- " + c.getName()));
    }
    
    private static void demonstrateBenefitsOfDI() {
        System.out.println("--- Benefits of Dependency Injection ---");
        
        System.out.println("\n✓ Loose Coupling:");
        System.out.println("  - CustomerService doesn't depend on concrete implementation");
        System.out.println("  - Can switch between real and mock repositories easily");
        
        System.out.println("\n✓ Testability:");
        System.out.println("  - Easy to inject mock objects for unit testing");
        System.out.println("  - Service logic can be tested in isolation");
        
        System.out.println("\n✓ Flexibility:");
        System.out.println("  - Can change repository implementation without modifying service");
        System.out.println("  - Supports multiple implementations (Database, File, Web Service, etc.)");
        
        System.out.println("\n✓ Maintainability:");
        System.out.println("  - Single Responsibility Principle followed");
        System.out.println("  - Dependencies are explicit and managed externally");
        
        System.out.println("\n✓ Reusability:");
        System.out.println("  - Service can be reused with different repository implementations");
        System.out.println("  - Repository implementations can be shared across services");
        
        // Demonstrate error handling
        System.out.println("\n--- Error Handling in Dependency Injection ---");
        try {
            CustomerService invalidService = new CustomerService(null);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Proper validation: " + e.getMessage());
        }
    }
}