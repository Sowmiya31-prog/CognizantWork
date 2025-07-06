import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a customer order with basic attributes
 */
class Order {
    private String orderId;
    private String customerName;
    private double totalPrice;
    
    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }
    
    // Getters
    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getTotalPrice() { return totalPrice; }
    
    // Setters
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    
    @Override
    public String toString() {
        return String.format("Order(%s, %s, $%.2f)", orderId, customerName, totalPrice);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return Double.compare(order.totalPrice, totalPrice) == 0 &&
               Objects.equals(orderId, order.orderId) &&
               Objects.equals(customerName, order.customerName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerName, totalPrice);
    }
}

/**
 * Class containing different sorting algorithms for Order objects
 */
class OrderSorter {
    
    /**
     * Bubble Sort implementation for sorting orders by total price
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    public static List<Order> bubbleSort(List<Order> orders) {
        List<Order> sortedOrders = new ArrayList<>(orders); // Create a copy
        int n = sortedOrders.size();
        
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // Flag to optimize - if no swaps occur, list is sorted
            
            // Last i elements are already in place
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements
                if (sortedOrders.get(j).getTotalPrice() > sortedOrders.get(j + 1).getTotalPrice()) {
                    // Swap elements
                    Collections.swap(sortedOrders, j, j + 1);
                    swapped = true;
                }
            }
            
            // If no swapping occurred, array is sorted
            if (!swapped) {
                break;
            }
        }
        
        return sortedOrders;
    }
    
    /**
     * Quick Sort implementation for sorting orders by total price
     * Time Complexity: O(n log n) average, O(n²) worst case
     * Space Complexity: O(log n) average
     */
    public static List<Order> quickSort(List<Order> orders) {
        List<Order> sortedOrders = new ArrayList<>(orders); // Create a copy
        quickSortRecursive(sortedOrders, 0, sortedOrders.size() - 1);
        return sortedOrders;
    }
    
    /**
     * Recursive helper function for Quick Sort
     */
    private static void quickSortRecursive(List<Order> orders, int low, int high) {
        if (low < high) {
            // Partition the array and get pivot index
            int pivotIndex = partition(orders, low, high);
            
            // Recursively sort elements before and after partition
            quickSortRecursive(orders, low, pivotIndex - 1);
            quickSortRecursive(orders, pivotIndex + 1, high);
        }
    }
    
    /**
     * Partition function for Quick Sort using last element as pivot
     */
    private static int partition(List<Order> orders, int low, int high) {
        // Choose the rightmost element as pivot
        double pivot = orders.get(high).getTotalPrice();
        
        // Index of smaller element (indicates right position of pivot)
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (orders.get(j).getTotalPrice() <= pivot) {
                i++;
                Collections.swap(orders, i, j);
            }
        }
        
        // Place pivot in correct position
        Collections.swap(orders, i + 1, high);
        return i + 1;
    }
    
    /**
     * Insertion Sort implementation for sorting orders by total price
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    public static List<Order> insertionSort(List<Order> orders) {
        List<Order> sortedOrders = new ArrayList<>(orders);
        
        for (int i = 1; i < sortedOrders.size(); i++) {
            Order key = sortedOrders.get(i);
            int j = i - 1;
            
            // Move elements greater than key one position ahead
            while (j >= 0 && sortedOrders.get(j).getTotalPrice() > key.getTotalPrice()) {
                sortedOrders.set(j + 1, sortedOrders.get(j));
                j--;
            }
            
            sortedOrders.set(j + 1, key);
        }
        
        return sortedOrders;
    }
    
    /**
     * Merge Sort implementation for sorting orders by total price
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    public static List<Order> mergeSort(List<Order> orders) {
        if (orders.size() <= 1) {
            return new ArrayList<>(orders);
        }
        
        // Divide the array into two halves
        int mid = orders.size() / 2;
        List<Order> left = mergeSort(orders.subList(0, mid));
        List<Order> right = mergeSort(orders.subList(mid, orders.size()));
        
        // Merge the sorted halves
        return merge(left, right);
    }
    
    /**
     * Merge function for Merge Sort
     */
    private static List<Order> merge(List<Order> left, List<Order> right) {
        List<Order> result = new ArrayList<>();
        int i = 0, j = 0;
        
        // Compare elements from both arrays and merge
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getTotalPrice() <= right.get(j).getTotalPrice()) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }
        
        // Add remaining elements
        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }
        
        return result;
    }
}

/**
 * Class for analyzing and comparing sorting algorithm performance
 */
class PerformanceAnalyzer {
    
    /**
     * Generate sample orders with random data
     */
    public static List<Order> generateSampleOrders(int count) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String orderId = String.format("ORD%04d", i + 1);
            String customerName = "Customer_" + (i + 1);
            double totalPrice = Math.round(ThreadLocalRandom.current().nextDouble(10.0, 1000.0) * 100.0) / 100.0;
            orders.add(new Order(orderId, customerName, totalPrice));
        }
        return orders;
    }
    
    /**
     * Measure the time taken by a sorting function
     */
    public static class SortingResult {
        public List<Order> sortedOrders;
        public long executionTime; // in nanoseconds
        
        public SortingResult(List<Order> sortedOrders, long executionTime) {
            this.sortedOrders = sortedOrders;
            this.executionTime = executionTime;
        }
        
        public double getExecutionTimeInSeconds() {
            return executionTime / 1_000_000_000.0;
        }
    }
    
    /**
     * Functional interface for sorting algorithms
     */
    @FunctionalInterface
    public interface SortingAlgorithm {
        List<Order> sort(List<Order> orders);
    }
    
    /**
     * Measure sorting time
     */
    public static SortingResult measureSortingTime(SortingAlgorithm algorithm, List<Order> orders) {
        long startTime = System.nanoTime();
        List<Order> sortedOrders = algorithm.sort(orders);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        return new SortingResult(sortedOrders, executionTime);
    }
    
    /**
     * Compare different sorting algorithms
     */
    public static void compareAlgorithms(List<Order> orders) {
        System.out.println("\n" + "=".repeat(60));
        System.out.printf("PERFORMANCE COMPARISON - %d orders%n", orders.size());
        System.out.println("=".repeat(60));
        
        Map<String, SortingAlgorithm> algorithms = new LinkedHashMap<>();
        algorithms.put("Bubble Sort", OrderSorter::bubbleSort);
        algorithms.put("Insertion Sort", OrderSorter::insertionSort);
        algorithms.put("Quick Sort", OrderSorter::quickSort);
        algorithms.put("Merge Sort", OrderSorter::mergeSort);
        
        List<String> results = new ArrayList<>();
        String fastest = "";
        double fastestTime = Double.MAX_VALUE;
        
        for (Map.Entry<String, SortingAlgorithm> entry : algorithms.entrySet()) {
            String name = entry.getKey();
            SortingAlgorithm algorithm = entry.getValue();
            
            try {
                SortingResult result = measureSortingTime(algorithm, orders);
                double timeInSeconds = result.getExecutionTimeInSeconds();
                
                System.out.printf("%-15s: %.6f seconds%n", name, timeInSeconds);
                
                if (timeInSeconds < fastestTime) {
                    fastestTime = timeInSeconds;
                    fastest = name;
                }
            } catch (Exception e) {
                System.out.printf("%-15s: Error - %s%n", name, e.getMessage());
            }
        }
        
        System.out.println("=".repeat(60));
        if (!fastest.isEmpty()) {
            System.out.printf("Fastest Algorithm: %s (%.6f seconds)%n", fastest, fastestTime);
        }
    }
    
    /**
     * Verify that sorting is correct
     */
    public static boolean verifySorting(List<Order> orders) {
        for (int i = 0; i < orders.size() - 1; i++) {
            if (orders.get(i).getTotalPrice() > orders.get(i + 1).getTotalPrice()) {
                return false;
            }
        }
        return true;
    }
}

/**
 * Main class for demonstrating the sorting algorithms
 */
public class OrderSortingSystem {
    
    /**
     * Explain different sorting algorithms
     */
    public static void explainAlgorithms() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("SORTING ALGORITHMS EXPLANATION");
        System.out.println("=".repeat(70));
        
        Map<String, Map<String, String>> explanations = new LinkedHashMap<>();
        
        // Bubble Sort
        Map<String, String> bubbleSort = new HashMap<>();
        bubbleSort.put("description", "Repeatedly steps through the list, compares adjacent elements and swaps them if they're in wrong order.");
        bubbleSort.put("time_complexity", "O(n²) - worst and average case, O(n) - best case");
        bubbleSort.put("space_complexity", "O(1)");
        bubbleSort.put("pros", "Simple to understand and implement, Stable sorting algorithm, In-place sorting");
        bubbleSort.put("cons", "Very inefficient for large datasets, O(n²) time complexity, More swaps compared to other algorithms");
        explanations.put("Bubble Sort", bubbleSort);
        
        // Insertion Sort
        Map<String, String> insertionSort = new HashMap<>();
        insertionSort.put("description", "Builds the final sorted array one item at a time, inserting each element in its correct position.");
        insertionSort.put("time_complexity", "O(n²) - worst and average case, O(n) - best case");
        insertionSort.put("space_complexity", "O(1)");
        insertionSort.put("pros", "Simple implementation, Efficient for small datasets, Stable and in-place, Online algorithm");
        insertionSort.put("cons", "Inefficient for large datasets, More writes than selection sort");
        explanations.put("Insertion Sort", insertionSort);
        
        // Quick Sort
        Map<String, String> quickSort = new HashMap<>();
        quickSort.put("description", "Divides array into partitions around a pivot, recursively sorts partitions.");
        quickSort.put("time_complexity", "O(n log n) - average case, O(n²) - worst case");
        quickSort.put("space_complexity", "O(log n) - average case");
        quickSort.put("pros", "Generally faster than other O(n²) algorithms, In-place sorting, Cache efficient");
        quickSort.put("cons", "Worst case O(n²), Not stable, Performance depends on pivot selection");
        explanations.put("Quick Sort", quickSort);
        
        // Merge Sort
        Map<String, String> mergeSort = new HashMap<>();
        mergeSort.put("description", "Divides array into halves, recursively sorts them, then merges sorted halves.");
        mergeSort.put("time_complexity", "O(n log n) - all cases");
        mergeSort.put("space_complexity", "O(n)");
        mergeSort.put("pros", "Guaranteed O(n log n), Stable sorting, Predictable performance");
        mergeSort.put("cons", "Requires additional memory, Not in-place, Overhead for small arrays");
        explanations.put("Merge Sort", mergeSort);
        
        for (Map.Entry<String, Map<String, String>> entry : explanations.entrySet()) {
            String algorithm = entry.getKey();
            Map<String, String> details = entry.getValue();
            
            System.out.println("\n" + algorithm.toUpperCase() + ":");
            System.out.println("Description: " + details.get("description"));
            System.out.println("Time Complexity: " + details.get("time_complexity"));
            System.out.println("Space Complexity: " + details.get("space_complexity"));
            System.out.println("Pros: " + details.get("pros"));
            System.out.println("Cons: " + details.get("cons"));
            System.out.println("-".repeat(70));
        }
    }
    
    /**
     * Explain why Quick Sort is preferred over Bubble Sort
     */
    public static void whyQuickSortPreferred() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("WHY QUICK SORT IS PREFERRED OVER BUBBLE SORT");
        System.out.println("=".repeat(70));
        
        String[][] comparisonPoints = {
            {"Time Complexity", "O(n²) - always quadratic", "O(n log n) average, O(n²) worst case", "Quick Sort - much better average performance"},
            {"Practical Performance", "Very slow for large datasets", "Fast for most real-world scenarios", "Quick Sort - significantly faster in practice"},
            {"Scalability", "Becomes unusable with large data", "Scales well with increasing data size", "Quick Sort - handles large datasets efficiently"},
            {"Industry Usage", "Only used for educational purposes", "Widely used in production systems", "Quick Sort - industry standard"},
            {"Adaptability", "Performance doesn't improve with partially sorted data", "Can be optimized for different scenarios", "Quick Sort - more adaptable"}
        };
        
        for (String[] point : comparisonPoints) {
            System.out.println("\n" + point[0] + ":");
            System.out.println("  Bubble Sort: " + point[1]);
            System.out.println("  Quick Sort:  " + point[2]);
            System.out.println("  Winner: " + point[3]);
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("CONCLUSION:");
        System.out.println("Quick Sort is preferred because:");
        System.out.println("1. Much better average-case time complexity O(n log n) vs O(n²)");
        System.out.println("2. Significantly faster in practice for real-world data");
        System.out.println("3. Scales well with large datasets");
        System.out.println("4. Used in production systems and standard libraries");
        System.out.println("5. Can be optimized further with techniques like randomized pivots");
        System.out.println("=".repeat(70));
    }
    
    /**
     * Demonstrate the sorting algorithms with sample data
     */
    public static void demonstrateSorting() {
        System.out.println("CUSTOMER ORDER SORTING SYSTEM");
        System.out.println("=".repeat(50));
        
        // Generate sample orders
        List<Order> orders = PerformanceAnalyzer.generateSampleOrders(10);
        
        System.out.println("\nOriginal Orders:");
        for (Order order : orders) {
            System.out.println("  " + order);
        }
        
        // Demonstrate Bubble Sort
        System.out.println("\n" + "=".repeat(50));
        System.out.println("BUBBLE SORT RESULTS:");
        System.out.println("=".repeat(50));
        PerformanceAnalyzer.SortingResult bubbleResult = PerformanceAnalyzer.measureSortingTime(
            OrderSorter::bubbleSort, orders
        );
        for (Order order : bubbleResult.sortedOrders) {
            System.out.println("  " + order);
        }
        System.out.printf("Execution Time: %.6f seconds%n", bubbleResult.getExecutionTimeInSeconds());
        System.out.println("Correctly Sorted: " + PerformanceAnalyzer.verifySorting(bubbleResult.sortedOrders));
        
        // Demonstrate Quick Sort
        System.out.println("\n" + "=".repeat(50));
        System.out.println("QUICK SORT RESULTS:");
        System.out.println("=".repeat(50));
        PerformanceAnalyzer.SortingResult quickResult = PerformanceAnalyzer.measureSortingTime(
            OrderSorter::quickSort, orders
        );
        for (Order order : quickResult.sortedOrders) {
            System.out.println("  " + order);
        }
        System.out.printf("Execution Time: %.6f seconds%n", quickResult.getExecutionTimeInSeconds());
        System.out.println("Correctly Sorted: " + PerformanceAnalyzer.verifySorting(quickResult.sortedOrders));
        
        // Performance comparison with larger dataset
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PERFORMANCE ANALYSIS");
        System.out.println("=".repeat(50));
        
        // Test with different sizes
        int[] sizes = {100, 500, 1000};
        
        for (int size : sizes) {
            List<Order> testOrders = PerformanceAnalyzer.generateSampleOrders(size);
            PerformanceAnalyzer.compareAlgorithms(testOrders);
        }
    }
    
    /**
     * Main method - entry point of the application
     */
    public static void main(String[] args) {
        // Run the demonstration
        explainAlgorithms();
        demonstrateSorting();
        whyQuickSortPreferred();
        
        // Interactive section
        System.out.println("\n" + "=".repeat(50));
        System.out.println("INTERACTIVE TESTING");
        System.out.println("=".repeat(50));
        System.out.println("You can now test the algorithms with custom data!");
        System.out.println("Example usage:");
        System.out.println("List<Order> orders = PerformanceAnalyzer.generateSampleOrders(50);");
        System.out.println("List<Order> sortedOrders = OrderSorter.quickSort(orders);");
        System.out.println("PerformanceAnalyzer.compareAlgorithms(orders);");
        
        // Demonstrate with user input simulation
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWould you like to test with a custom dataset size? (y/n):");
        
        try {
            String input = scanner.nextLine();
            if (input.toLowerCase().startsWith("y")) {
                System.out.print("Enter the number of orders to generate: ");
                int customSize = scanner.nextInt();
                
                if (customSize > 0 && customSize <= 10000) {
                    List<Order> customOrders = PerformanceAnalyzer.generateSampleOrders(customSize);
                    System.out.println("\nTesting with " + customSize + " orders:");
                    PerformanceAnalyzer.compareAlgorithms(customOrders);
                } else {
                    System.out.println("Please enter a number between 1 and 10000.");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Ending program.");
        } finally {
            scanner.close();
        }
        
        System.out.println("\nThank you for using the Customer Order Sorting System!");
    }
}