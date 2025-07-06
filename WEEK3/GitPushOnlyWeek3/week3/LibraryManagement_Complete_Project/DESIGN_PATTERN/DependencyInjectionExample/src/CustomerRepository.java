import java.util.List;

interface CustomerRepository {
    Customer findCustomerById(String id);
    List<Customer> findAllCustomers();
    void saveCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(String id);
    List<Customer> findCustomersByName(String name);
    boolean customerExists(String id);
}