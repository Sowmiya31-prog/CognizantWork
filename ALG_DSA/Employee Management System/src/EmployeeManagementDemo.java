/**
 * Employee Management System using Arrays
 * 
 * This system demonstrates array operations and their time complexities
 * for managing employee records efficiently.
 */

// Employee class to represent individual employee records
class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;
    
    // Constructor
    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    
    // Getters
    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setPosition(String position) { this.position = position; }
    public void setSalary(double salary) { this.salary = salary; }
    
    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Position: %s, Salary: $%.2f", 
                           employeeId, name, position, salary);
    }
}

// Employee Management System class
class EmployeeManagementSystem {
    private Employee[] employees;
    private int capacity;
    private int size;
    
    // Constructor - Initialize array with given capacity
    public EmployeeManagementSystem(int capacity) {
        this.capacity = capacity;
        this.employees = new Employee[capacity];
        this.size = 0;
    }
    
    /**
     * Add employee to the system
     * Time Complexity: O(1) - if adding at end
     * Space Complexity: O(1)
     */
    public boolean addEmployee(Employee employee) {
        if (size >= capacity) {
            System.out.println("Error: Maximum capacity reached!");
            return false;
        }
        
        // Check for duplicate employee ID
        if (searchEmployee(employee.getEmployeeId()) != null) {
            System.out.println("Error: Employee with ID " + employee.getEmployeeId() + " already exists!");
            return false;
        }
        
        employees[size] = employee;
        size++;
        System.out.println("Employee added successfully: " + employee.getName());
        return true;
    }
    
    /**
     * Search employee by ID
     * Time Complexity: O(n) - Linear search
     * Space Complexity: O(1)
     */
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i] != null && employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }
    
    /**
     * Search employee by name
     * Time Complexity: O(n) - Linear search
     * Space Complexity: O(1)
     */
    public Employee searchEmployeeByName(String name) {
        for (int i = 0; i < size; i++) {
            if (employees[i] != null && employees[i].getName().equalsIgnoreCase(name)) {
                return employees[i];
            }
        }
        return null;
    }
    
    /**
     * Traverse and display all employees
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void traverseEmployees() {
        if (size == 0) {
            System.out.println("No employees found in the system.");
            return;
        }
        
        System.out.println("\n=== Employee List ===");
        System.out.println("Total Employees: " + size);
        System.out.println("------------------------");
        
        for (int i = 0; i < size; i++) {
            if (employees[i] != null) {
                System.out.println((i + 1) + ". " + employees[i]);
            }
        }
        System.out.println("------------------------\n");
    }
    
    /**
     * Delete employee by ID
     * Time Complexity: O(n) - need to find and shift elements
     * Space Complexity: O(1)
     */
    public boolean deleteEmployee(int employeeId) {
        int indexToDelete = -1;
        
        // Find the employee to delete
        for (int i = 0; i < size; i++) {
            if (employees[i] != null && employees[i].getEmployeeId() == employeeId) {
                indexToDelete = i;
                break;
            }
        }
        
        if (indexToDelete == -1) {
            System.out.println("Error: Employee with ID " + employeeId + " not found!");
            return false;
        }
        
        String deletedEmployeeName = employees[indexToDelete].getName();
        
        // Shift elements to the left to fill the gap
        for (int i = indexToDelete; i < size - 1; i++) {
            employees[i] = employees[i + 1];
        }
        
        // Clear the last element and decrease size
        employees[size - 1] = null;
        size--;
        
        System.out.println("Employee deleted successfully: " + deletedEmployeeName);
        return true;
    }
    
    /**
     * Update employee information
     * Time Complexity: O(n) - need to search first
     * Space Complexity: O(1)
     */
    public boolean updateEmployee(int employeeId, String newName, String newPosition, double newSalary) {
        Employee employee = searchEmployee(employeeId);
        if (employee == null) {
            System.out.println("Error: Employee with ID " + employeeId + " not found!");
            return false;
        }
        
        employee.setName(newName);
        employee.setPosition(newPosition);
        employee.setSalary(newSalary);
        
        System.out.println("Employee updated successfully: " + employee);
        return true;
    }
    
    /**
     * Get employees by position
     * Time Complexity: O(n)
     * Space Complexity: O(k) where k is number of matching employees
     */
    public void getEmployeesByPosition(String position) {
        System.out.println("\n=== Employees in position: " + position + " ===");
        boolean found = false;
        
        for (int i = 0; i < size; i++) {
            if (employees[i] != null && employees[i].getPosition().equalsIgnoreCase(position)) {
                System.out.println(employees[i]);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No employees found in position: " + position);
        }
        System.out.println();
    }
    
    /**
     * Get system statistics
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void getSystemStats() {
        if (size == 0) {
            System.out.println("No employees in the system.");
            return;
        }
        
        double totalSalary = 0;
        double maxSalary = employees[0].getSalary();
        double minSalary = employees[0].getSalary();
        
        for (int i = 0; i < size; i++) {
            if (employees[i] != null) {
                double salary = employees[i].getSalary();
                totalSalary += salary;
                maxSalary = Math.max(maxSalary, salary);
                minSalary = Math.min(minSalary, salary);
            }
        }
        
        System.out.println("\n=== System Statistics ===");
        System.out.println("Total Employees: " + size);
        System.out.println("Capacity: " + capacity);
        System.out.println("Capacity Utilization: " + String.format("%.1f%%", (size * 100.0) / capacity));
        System.out.println("Total Salary Budget: $" + String.format("%.2f", totalSalary));
        System.out.println("Average Salary: $" + String.format("%.2f", totalSalary / size));
        System.out.println("Highest Salary: $" + String.format("%.2f", maxSalary));
        System.out.println("Lowest Salary: $" + String.format("%.2f", minSalary));
        System.out.println("=========================\n");
    }
    
    // Getter methods
    public int getSize() { return size; }
    public int getCapacity() { return capacity; }
    public boolean isFull() { return size >= capacity; }
    public boolean isEmpty() { return size == 0; }
}

// Main class to demonstrate the Employee Management System
public class EmployeeManagementDemo {
    public static void main(String[] args) {
        // Create an employee management system with capacity of 10
        EmployeeManagementSystem ems = new EmployeeManagementSystem(10);
        
        System.out.println("=== Employee Management System Demo ===\n");
        
        // Demonstrate adding employees
        System.out.println("1. Adding Employees:");
        System.out.println("-------------------");
        ems.addEmployee(new Employee(101, "John Doe", "Software Engineer", 75000));
        ems.addEmployee(new Employee(102, "Jane Smith", "Project Manager", 85000));
        ems.addEmployee(new Employee(103, "Mike Johnson", "Software Engineer", 70000));
        ems.addEmployee(new Employee(104, "Sarah Wilson", "HR Manager", 65000));
        ems.addEmployee(new Employee(105, "David Brown", "Senior Developer", 90000));
        
        // Try adding duplicate
        ems.addEmployee(new Employee(101, "Duplicate John", "Tester", 50000));
        
        // Demonstrate traversing
        System.out.println("\n2. Traversing All Employees:");
        System.out.println("----------------------------");
        ems.traverseEmployees();
        
        // Demonstrate searching
        System.out.println("3. Searching Employees:");
        System.out.println("----------------------");
        Employee found = ems.searchEmployee(103);
        if (found != null) {
            System.out.println("Found employee: " + found);
        }
        
        Employee notFound = ems.searchEmployee(999);
        if (notFound == null) {
            System.out.println("Employee with ID 999 not found");
        }
        
        // Search by name
        Employee foundByName = ems.searchEmployeeByName("Jane Smith");
        if (foundByName != null) {
            System.out.println("Found by name: " + foundByName);
        }
        
        // Demonstrate updating
        System.out.println("\n4. Updating Employee:");
        System.out.println("--------------------");
        ems.updateEmployee(102, "Jane Smith-Johnson", "Senior Project Manager", 95000);
        
        // Get employees by position
        System.out.println("\n5. Employees by Position:");
        System.out.println("------------------------");
        ems.getEmployeesByPosition("Software Engineer");
        
        // Show system statistics
        System.out.println("6. System Statistics:");
        System.out.println("--------------------");
        ems.getSystemStats();
        
        // Demonstrate deleting
        System.out.println("7. Deleting Employee:");
        System.out.println("--------------------");
        ems.deleteEmployee(104);
        ems.deleteEmployee(999); // Try deleting non-existent employee
        
        // Show final state
        System.out.println("\n8. Final Employee List:");
        System.out.println("----------------------");
        ems.traverseEmployees();
        
        // Final statistics
        ems.getSystemStats();
        
        // Demonstrate time complexity analysis
        System.out.println("=== Time Complexity Analysis ===");
        System.out.println("Add Employee: O(1) - Constant time (adding at end)");
        System.out.println("Search Employee: O(n) - Linear time (unsorted array)");
        System.out.println("Traverse All: O(n) - Linear time (visit each element)");
        System.out.println("Delete Employee: O(n) - Linear time (search + shift elements)");
        System.out.println("Update Employee: O(n) - Linear time (search first)");
        
        System.out.println("\n=== Array Advantages ===");
        System.out.println("• Fast random access by index O(1)");
        System.out.println("• Cache-friendly memory layout");
        System.out.println("• Simple memory management");
        System.out.println("• Predictable memory usage");
        
        System.out.println("\n=== Array Limitations ===");
        System.out.println("• Fixed size (cannot grow dynamically)");
        System.out.println("• Insertion/deletion in middle is expensive O(n)");
        System.out.println("• Searching is slow O(n) for unsorted arrays");
        System.out.println("• Memory waste if not fully utilized");
        
        System.out.println("\n=== When to Use Arrays ===");
        System.out.println("• When size is known and relatively fixed");
        System.out.println("• Need fast random access to elements");
        System.out.println("• Memory usage needs to be predictable");
        System.out.println("• Simple data structure requirements");
        System.out.println("• Performance-critical applications");
    }
}