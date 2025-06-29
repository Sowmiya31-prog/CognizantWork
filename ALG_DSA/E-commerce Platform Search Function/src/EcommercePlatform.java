import java.util.*;

/**
 * E-commerce Platform Search Function Demo
 * Demonstrates linear vs binary search with performance analysis
 */
public class EcommercePlatform {
    private Product[] products;
    private Product[] productsSortedByName;
    private Product[] productsSortedById;
    private Product[] productsSortedByPrice;
    private Scanner scanner;
    
    public EcommercePlatform() {
        scanner = new Scanner(System.in);
        initializeSampleData();
        prepareSortedArrays();
    }
    
    /**
     * Initialize with comprehensive sample data
     */
    private void initializeSampleData() {
        products = new Product[] {
            // Electronics
            new Product("E001", "iPhone 15 Pro", "Electronics", 999.99, 25, 4.8),
            new Product("E002", "Samsung Galaxy S24", "Electronics", 899.99, 30, 4.7),
            new Product("E003", "MacBook Pro M3", "Electronics", 1999.99, 15, 4.9),
            new Product("E004", "Dell XPS 13", "Electronics", 1299.99, 20, 4.6),
            new Product("E005", "iPad Air", "Electronics", 599.99, 40, 4.5),
            new Product("E006", "AirPods Pro", "Electronics", 249.99, 60, 4.4),
            new Product("E007", "Sony WH-1000XM5", "Electronics", 349.99, 35, 4.8),
            new Product("E008", "Nintendo Switch", "Electronics", 299.99, 45, 4.7),
            
            // Clothing
            new Product("C001", "Levi's 501 Jeans", "Clothing", 89.99, 100, 4.3),
            new Product("C002", "Nike Air Max 270", "Clothing", 149.99, 80, 4.6),
            new Product("C003", "Adidas Ultraboost", "Clothing", 179.99, 70, 4.5),
            new Product("C004", "North Face Jacket", "Clothing", 199.99, 50, 4.7),
            new Product("C005", "Champion Hoodie", "Clothing", 49.99, 120, 4.2),
            new Product("C006", "Ray-Ban Aviators", "Clothing", 129.99, 90, 4.4),
            new Product("C007", "Converse Chuck Taylor", "Clothing", 59.99, 110, 4.1),
            
            // Home & Garden
            new Product("H001", "Dyson V15 Vacuum", "Home", 749.99, 12, 4.8),
            new Product("H002", "KitchenAid Mixer", "Home", 399.99, 25, 4.9),
            new Product("H003", "Instant Pot Duo", "Home", 99.99, 75, 4.6),
            new Product("H004", "Roomba i7+", "Home", 599.99, 18, 4.5),
            new Product("H005", "Philips Hue Starter Kit", "Home", 199.99, 40, 4.3),
            new Product("H006", "Weber Genesis Grill", "Home", 899.99, 8, 4.7),
            
            // Books
            new Product("B001", "The Psychology of Money", "Books", 19.99, 150, 4.6),
            new Product("B002", "Atomic Habits", "Books", 18.99, 200, 4.8),
            new Product("B003", "Sapiens", "Books", 22.99, 120, 4.7),
            new Product("B004", "Think and Grow Rich", "Books", 15.99, 180, 4.4),
            new Product("B005", "The 7 Habits", "Books", 17.99, 160, 4.5),
            
            // Sports
            new Product("S001", "Peloton Bike+", "Sports", 2495.99, 5, 4.6),
            new Product("S002", "Bowflex Dumbbells", "Sports", 349.99, 30, 4.7),
            new Product("S003", "Yoga Mat Premium", "Sports", 79.99, 85, 4.4),
            new Product("S004", "Fitbit Charge 5", "Sports", 179.99, 65, 4.3),
            new Product("S005", "Hydro Flask Bottle", "Sports", 39.99, 200, 4.5)
        };
        
        System.out.println("Initialized " + products.length + " products across multiple categories.");
    }
    
    /**
     * Prepare sorted arrays for binary search
     */
    private void prepareSortedArrays() {
        // Sort by name
        productsSortedByName = products.clone();
        SearchAlgorithms.sortProducts(productsSortedByName, "name");
        
        // Sort by ID
        productsSortedById = products.clone();
        SearchAlgorithms.sortProducts(productsSortedById, "id");
        
        // Sort by price
        productsSortedByPrice = products.clone();
        SearchAlgorithms.sortProducts(productsSortedByPrice, "price");
        
        System.out.println("Prepared sorted arrays for binary search optimization.");
    }
    
    public void showMainMenu() {
        while (true) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("         E-COMMERCE PLATFORM SEARCH DEMO");
            System.out.println("=".repeat(60));
            System.out.println("1.  Linear Search by Name");
            System.out.println("2.  Binary Search by Name");
            System.out.println("3.  Linear Search by ID");
            System.out.println("4.  Binary Search by ID");
            System.out.println("5.  Search by Category");
            System.out.println("6.  Price Range Search");
            System.out.println("7.  Advanced Multi-Criteria Search");
            System.out.println("8.  Fuzzy Search (Typo-Tolerant)");
            System.out.println("9.  Performance Comparison");
            System.out.println("10. Bulk Performance Test");
            System.out.println("11. View All Products");
            System.out.println("12. Algorithm Complexity Analysis");
            System.out.println("0.  Exit");
            System.out.println("=".repeat(60));
            System.out.print("Choose an option: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1: performLinearSearchByName(); break;
                case 2: performBinarySearchByName(); break;
                case 3: performLinearSearchById(); break;
                case 4: performBinarySearchById(); break;
                case 5: performCategorySearch(); break;
                case 6: performPriceRangeSearch(); break;
                case 7: performAdvancedSearch(); break;
                case 8: performFuzzySearch(); break;
                case 9: performanceComparison(); break;
                case 10: bulkPerformanceTest(); break;
                case 11: viewAllProducts(); break;
                case 12: showComplexityAnalysis(); break;
                case 0:
                    System.out.println("Thank you for using E-commerce Platform Search Demo!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    private void performLinearSearchByName() {
        System.out.println("\n--- Linear Search by Name ---");
        System.out.print("Enter product name to search: ");
        String searchName = scanner.nextLine().trim();
        
        SearchAlgorithms.SearchResult result = SearchAlgorithms.linearSearchByName(products, searchName);
        displaySearchResult(result);
    }
    
    private void performBinarySearchByName() {
        System.out.println("\n--- Binary Search by Name ---");
        System.out.print("Enter product name to search: ");
        String searchName = scanner.nextLine().trim();
        
        SearchAlgorithms.SearchResult result = SearchAlgorithms.binarySearchByName(productsSortedByName, searchName);
        displaySearchResult(result);
    }
    
    private void performLinearSearchById() {
        System.out.println("\n--- Linear Search by ID ---");
        System.out.print("Enter product ID to search: ");
        String searchId = scanner.nextLine().trim();
        
        SearchAlgorithms.SearchResult result = SearchAlgorithms.linearSearchById(products, searchId);
        displaySearchResult(result);
    }
    
    private void performBinarySearchById() {
        System.out.println("\n--- Binary Search by ID ---");
        System.out.print("Enter product ID to search: ");
        String searchId = scanner.nextLine().trim();
        
        SearchAlgorithms.SearchResult result = SearchAlgorithms.binarySearchById(productsSortedById, searchId);
        displaySearchResult(result);
    }
    
    private void performCategorySearch() {
        System.out.println("\n--- Category Search ---");
        System.out.println("Available categories: Electronics, Clothing, Home, Books, Sports");
        System.out.print("Enter category: ");
        String category = scanner.nextLine().trim();
        
        long startTime = System.nanoTime();
        List<Product> results = SearchAlgorithms.linearSearchByCategory(products, category);
        long endTime = System.nanoTime();
        
        System.out.println("\nSearch Results:");
        if (results.isEmpty()) {
            System.out.println("No products found in category: " + category);
        } else {
            System.out.println("Found " + results.size() + " products in category '" + category + "':");
            System.out.println("-".repeat(80));
            results.forEach(product -> System.out.println(product.toSearchResult()));
        }
        
        System.out.printf("Search completed in %.3f ms\n", (endTime - startTime) / 1_000_000.0);
    }
    
    private void performPriceRangeSearch() {
        System.out.println("\n--- Price Range Search ---");
        System.out.print("Enter minimum price: $");
        double minPrice = getDoubleInput();
        System.out.print("Enter maximum price: $");
        double maxPrice = getDoubleInput();
        
        long startTime = System.nanoTime();
        List<Product> results = SearchAlgorithms.binarySearchPriceRange(productsSortedByPrice, minPrice, maxPrice);
        long endTime = System.nanoTime();
        
        System.out.println("\nPrice Range Search Results:");
        if (results.isEmpty()) {
            System.out.printf("No products found in price range $%.2f - $%.2f\n", minPrice, maxPrice);
        } else {
            System.out.printf("Found %d products in price range $%.2f - $%.2f:\n", results.size(), minPrice, maxPrice);
            System.out.println("-".repeat(80));
            results.forEach(product -> System.out.println(product.toSearchResult()));
        }
        
        System.out.printf("Search completed in %.3f ms using binary search optimization\n", (endTime - startTime) / 1_000_000.0);
    }
    
    private void performAdvancedSearch() {
        System.out.println("\n--- Advanced Multi-Criteria Search ---");
        
        System.out.print("Enter name keyword (or press Enter to skip): ");
        String nameKeyword = scanner.nextLine().trim();
        if (nameKeyword.isEmpty()) nameKeyword = null;
        
        System.out.print("Enter category (or press Enter to skip): ");
        String category = scanner.nextLine().trim();
        if (category.isEmpty()) category = null;
        
        System.out.print("Enter minimum price: $");
        double minPrice = getDoubleInput();
        
        System.out.print("Enter maximum price: $");
        double maxPrice = getDoubleInput();
        
        System.out.print("Enter minimum rating (0-5): ");
        double minRating = getDoubleInput();
        
        System.out.print("Only in-stock products? (y/n): ");
        boolean inStockOnly = scanner.nextLine().trim().toLowerCase().startsWith("y");
        
        long startTime = System.nanoTime();
        List<Product> results = SearchAlgorithms.advancedSearch(products, nameKeyword, category, 
                                                              minPrice, maxPrice, minRating, inStockOnly);
        long endTime = System.nanoTime();
        
        System.out.println("\nAdvanced Search Results:");
        if (results.isEmpty()) {
            System.out.println("No products found matching the specified criteria.");
        } else {
            System.out.printf("Found %d products matching criteria:\n", results.size());
            System.out.println("-".repeat(80));
            results.forEach(product -> System.out.println(product.toSearchResult()));
        }
        
        System.out.printf("Search completed in %.3f ms\n", (endTime - startTime) / 1_000_000.0);
    }
    
    private void performFuzzySearch() {
        System.out.println("\n--- Fuzzy Search (Typo-Tolerant) ---");
        System.out.print("Enter product name (typos allowed): ");
        String searchTerm = scanner.nextLine().trim();
        
        System.out.print("Enter maximum edit distance (1-3 recommended): ");
        int maxDistance = getIntInput();
        
        long startTime = System.nanoTime();
        List<Product> results = SearchAlgorithms.fuzzySearchByName(products, searchTerm, maxDistance);
        long endTime = System.nanoTime();
        
        System.out.println("\nFuzzy Search Results:");
        if (results.isEmpty()) {
            System.out.printf("No products found similar to '%s' within edit distance %d\n", searchTerm, maxDistance);
        } else {
            System.out.printf("Found %d products similar to '%s':\n", results.size(), searchTerm);
            System.out.println("-".repeat(80));
            for (int i = 0; i < Math.min(results.size(), 10); i++) { // Show max 10 results
                Product product = results.get(i);
                int distance = calculateEditDistance(product.getProductName().toLowerCase(), searchTerm.toLowerCase());
                System.out.printf("Distance: %d | %s\n", distance, product.toSearchResult());
            }
            if (results.size() > 10) {
                System.out.printf("... and %d more results\n", results.size() - 10);
            }
        }
        
        System.out.printf("Fuzzy search completed in %.3f ms\n", (endTime - startTime) / 1_000_000.0);
    }
    
    private void performanceComparison() {
        System.out.println("\n--- Performance Comparison: Linear vs Binary Search ---");
        System.out.print("Enter product name to search: ");
        String searchName = scanner.nextLine().trim();
        
        System.out.println("\nPerforming both searches...");
        
        // Linear search
        SearchAlgorithms.SearchResult linearResult = SearchAlgorithms.linearSearchByName(products, searchName);
        
        // Binary search
        SearchAlgorithms.SearchResult binaryResult = SearchAlgorithms.binarySearchByName(productsSortedByName, searchName);
        
        // Display comparison
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    PERFORMANCE COMPARISON");
        System.out.println("=".repeat(70));
        System.out.printf("%-20s | %-15s | %-12s | %-10s\n", "Algorithm", "Result", "Comparisons", "Time (ms)");
        System.out.println("-".repeat(70));
        System.out.printf("%-20s | %-15s | %-12d | %-10.3f\n", 
                linearResult.getAlgorithm(), 
                linearResult.isFound() ? "FOUND" : "NOT FOUND",
                linearResult.getComparisons(),
                linearResult.getExecutionTimeMillis());
        System.out.printf("%-20s | %-15s | %-12d | %-10.3f\n", 
                binaryResult.getAlgorithm(), 
                binaryResult.isFound() ? "FOUND" : "NOT FOUND",
                binaryResult.getComparisons(),
                binaryResult.getExecutionTimeMillis());
        
        // Performance analysis
        if (linearResult.isFound() && binaryResult.isFound()) {
            double speedup = (double) linearResult.getComparisons() / binaryResult.getComparisons();
            System.out.println("-".repeat(70));
            System.out.printf("Binary search used %.1fx fewer comparisons\n", speedup);
            
            if (linearResult.getExecutionTimeNanos() > binaryResult.getExecutionTimeNanos()) {
                double timeSpeedup = (double) linearResult.getExecutionTimeNanos() / binaryResult.getExecutionTimeNanos();
                System.out.printf("Binary search was %.1fx faster\n", timeSpeedup);
            }
        }
        System.out.println("=".repeat(70));
    }
    
    private void bulkPerformanceTest() {
        System.out.println("\n--- Bulk Performance Test ---");
        System.out.print("Enter number of search operations to perform: ");
        int numOperations = getIntInput();
        
        // Prepare test data - use existing product names
        String[] testQueries = new String[numOperations];
        Random random = new Random();
        for (int i = 0; i < numOperations; i++) {
            testQueries[i] = products[random.nextInt(products.length)].getProductName();
        }
        
        System.out.println("\nTesting " + numOperations + " search operations...");
        
        // Linear search bulk test
        long linearStartTime = System.nanoTime();
        int linearTotalComparisons = 0;
        for (String query : testQueries) {
            SearchAlgorithms.SearchResult result = SearchAlgorithms.linearSearchByName(products, query);
            linearTotalComparisons += result.getComparisons();
        }
        long linearEndTime = System.nanoTime();
        
        // Binary search bulk test
        long binaryStartTime = System.nanoTime();
        int binaryTotalComparisons = 0;
        for (String query : testQueries) {
            SearchAlgorithms.SearchResult result = SearchAlgorithms.binarySearchByName(productsSortedByName, query);
            binaryTotalComparisons += result.getComparisons();
        }
        long binaryEndTime = System.nanoTime();
        
        // Display results
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                     BULK PERFORMANCE TEST RESULTS");
        System.out.println("=".repeat(80));
        System.out.printf("%-20s | %-15s | %-18s | %-12s\n", "Algorithm", "Total Time (ms)", "Avg Time per Op (μs)", "Total Comparisons");
        System.out.println("-".repeat(80));
        
        double linearTotalMs = (linearEndTime - linearStartTime) / 1_000_000.0;
        double linearAvgMicros = (linearEndTime - linearStartTime) / 1_000.0 / numOperations;
        System.out.printf("%-20s | %-15.3f | %-18.3f | %-12d\n", 
                "Linear Search", linearTotalMs, linearAvgMicros, linearTotalComparisons);
        
        double binaryTotalMs = (binaryEndTime - binaryStartTime) / 1_000_000.0;
        double binaryAvgMicros = (binaryEndTime - binaryStartTime) / 1_000.0 / numOperations;
        System.out.printf("%-20s | %-15.3f | %-18.3f | %-12d\n", 
                "Binary Search", binaryTotalMs, binaryAvgMicros, binaryTotalComparisons);
        
        System.out.println("-".repeat(80));
        System.out.printf("Binary search performance improvement:\n");
        System.out.printf("- %.1fx fewer total comparisons\n", (double) linearTotalComparisons / binaryTotalComparisons);
        System.out.printf("- %.1fx faster execution time\n", linearTotalMs / binaryTotalMs);
        System.out.println("=".repeat(80));
    }
    
    private void viewAllProducts() {
        System.out.println("\n--- All Products ---");
        System.out.printf("%-8s | %-25s | %-12s | %-10s | %-5s | %-6s\n", 
                "ID", "Name", "Category", "Price", "Stock", "Rating");
        System.out.println("-".repeat(80));
        
        for (Product product : products) {
            System.out.println(product.toSearchResult());
        }
        
        System.out.println("-".repeat(80));
        System.out.println("Total products: " + products.length);
    }
    
    private void showComplexityAnalysis() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    ALGORITHM COMPLEXITY ANALYSIS");
        System.out.println("=".repeat(80));
        
        System.out.println("\n1. TIME COMPLEXITY COMPARISON:");
        System.out.println("-".repeat(50));
        System.out.printf("%-20s | %-12s | %-12s | %-12s\n", "Algorithm", "Best Case", "Average Case", "Worst Case");
        System.out.println("-".repeat(50));
        System.out.printf("%-20s | %-12s | %-12s | %-12s\n", "Linear Search", "O(1)", "O(n)", "O(n)");
        System.out.printf("%-20s | %-12s | %-12s | %-12s\n", "Binary Search", "O(1)", "O(log n)", "O(log n)");
        
        System.out.println("\n2. SPACE COMPLEXITY:");
        System.out.println("-".repeat(30));
        System.out.printf("%-20s | %-8s\n", "Algorithm", "Space");
        System.out.println("-".repeat(30));
        System.out.printf("%-20s | %-8s\n", "Linear Search", "O(1)");
        System.out.printf("%-20s | %-8s\n", "Binary Search", "O(1)");
        
        System.out.println("\n3. PRACTICAL PERFORMANCE (Current Dataset: " + products.length + " products):");
        System.out.println("-".repeat(60));
        System.out.printf("%-20s | %-15s | %-20s\n", "Algorithm", "Max Comparisons", "Expected Comparisons");
        System.out.println("-".repeat(60));
        System.out.printf("%-20s | %-15d | %-20.1f\n", "Linear Search", products.length, products.length / 2.0);
        System.out.printf("%-20s | %-15d | %-20.1f\n", "Binary Search", 
                (int) Math.ceil(Math.log(products.length) / Math.log(2)), 
                Math.log(products.length) / Math.log(2));
        
        System.out.println("\n4. ALGORITHM SUITABILITY:");
        System.out.println("-".repeat(40));
        System.out.println("Linear Search is better when:");
        System.out.println("• Data is unsorted and sorting cost is high");
        System.out.println("• Dataset is small (< 100 items)");
        System.out.println("• Memory is extremely limited");
        System.out.println("• Need to find all occurrences");
        
        System.out.println("\nBinary Search is better when:");
        System.out.println("• Data can be kept sorted");
        System.out.println("• Dataset is large (> 100 items)");
        System.out.println("• Frequent search operations");
        System.out.println("• Need fastest possible search");
        
        System.out.println("\n5. REAL-WORLD CONSIDERATIONS:");
        System.out.println("-".repeat(40));
        System.out.println("• Sorting overhead: O(n log n) initial cost");
        System.out.println("• Memory access patterns affect real performance");
        System.out.println("• Modern CPUs have cache optimizations");
        System.out.println("• Database systems use indexed searches (similar to binary search)");
        System.out.println("=".repeat(80));
    }
    
    private void displaySearchResult(SearchAlgorithms.SearchResult result) {
        System.out.println("\n--- Search Result ---");
        System.out.println(result);
        
        if (result.isFound()) {
            System.out.println("\nProduct Details:");
            System.out.println(result.getProduct());
        }
        
        // Performance insights
        System.out.println("\nPerformance Analysis:");
        if (result.getComparisons() == 1) {
            System.out.println("• Excellent! Found on first comparison (best case)");
        } else if (result.getComparisons() <= 3) {
            System.out.println("• Very good performance - found quickly");
        } else if (result.getComparisons() <= products.length / 2) {
            System.out.println("• Good performance - better than average case");
        } else {
            System.out.println("• Performance could be improved with binary search");
        }
    }
    
    // Helper method for fuzzy search
    private int calculateEditDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(
                        Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1),
                        dp[i-1][j-1] + (s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1)
                    );
                }
            }
        }
        
        return dp[s1.length()][s2.length()];
    }
    
    // Utility methods for input handling
    private int getIntInput() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    private double getDoubleInput() {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Starting E-commerce Platform Search Demo...");
        EcommercePlatform platform = new EcommercePlatform();
        platform.showMainMenu();
    }
}