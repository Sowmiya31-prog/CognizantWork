import java.util.*;

/**
 * SearchAlgorithms class implementing linear and binary search
 * with performance tracking and comparison capabilities
 */
public class SearchAlgorithms {
    
    // Inner class to track search performance
    public static class SearchResult {
        private Product product;
        private int comparisons;
        private long executionTimeNanos;
        private String algorithm;
        private boolean found;
        
        public SearchResult(Product product, int comparisons, long executionTime, String algorithm, boolean found) {
            this.product = product;
            this.comparisons = comparisons;
            this.executionTimeNanos = executionTime;
            this.algorithm = algorithm;
            this.found = found;
        }
        
        // Getters
        public Product getProduct() { return product; }
        public int getComparisons() { return comparisons; }
        public long getExecutionTimeNanos() { return executionTimeNanos; }
        public double getExecutionTimeMillis() { return executionTimeNanos / 1_000_000.0; }
        public String getAlgorithm() { return algorithm; }
        public boolean isFound() { return found; }
        
        @Override
        public String toString() {
            return String.format("%s: %s | Comparisons: %d | Time: %.3f ms", 
                    algorithm, found ? "FOUND" : "NOT FOUND", comparisons, getExecutionTimeMillis());
        }
    }
    
    /**
     * Linear Search by Product Name
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static SearchResult linearSearchByName(Product[] products, String searchName) {
        long startTime = System.nanoTime();
        int comparisons = 0;
        
        for (int i = 0; i < products.length; i++) {
            comparisons++;
            if (products[i].getProductName().equalsIgnoreCase(searchName)) {
                long endTime = System.nanoTime();
                return new SearchResult(products[i], comparisons, endTime - startTime, "Linear Search", true);
            }
        }
        
        long endTime = System.nanoTime();
        return new SearchResult(null, comparisons, endTime - startTime, "Linear Search", false);
    }
    
    /**
     * Linear Search by Product ID
     * Time Complexity: O(n)
     */
    public static SearchResult linearSearchById(Product[] products, String searchId) {
        long startTime = System.nanoTime();
        int comparisons = 0;
        
        for (int i = 0; i < products.length; i++) {
            comparisons++;
            if (products[i].getProductId().equalsIgnoreCase(searchId)) {
                long endTime = System.nanoTime();
                return new SearchResult(products[i], comparisons, endTime - startTime, "Linear Search (ID)", true);
            }
        }
        
        long endTime = System.nanoTime();
        return new SearchResult(null, comparisons, endTime - startTime, "Linear Search (ID)", false);
    }
    
    /**
     * Linear Search by Category
     * Returns all products in the category
     * Time Complexity: O(n)
     */
    public static List<Product> linearSearchByCategory(Product[] products, String category) {
        List<Product> results = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                results.add(product);
            }
        }
        
        return results;
    }
    
    /**
     * Binary Search by Product Name (requires sorted array)
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static SearchResult binarySearchByName(Product[] sortedProducts, String searchName) {
        long startTime = System.nanoTime();
        int comparisons = 0;
        int left = 0;
        int right = sortedProducts.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            comparisons++;
            
            int comparison = sortedProducts[mid].getProductName().compareToIgnoreCase(searchName);
            
            if (comparison == 0) {
                long endTime = System.nanoTime();
                return new SearchResult(sortedProducts[mid], comparisons, endTime - startTime, "Binary Search", true);
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        long endTime = System.nanoTime();
        return new SearchResult(null, comparisons, endTime - startTime, "Binary Search", false);
    }
    
    /**
     * Binary Search by Product ID (requires sorted array by ID)
     * Time Complexity: O(log n)
     */
    public static SearchResult binarySearchById(Product[] sortedProducts, String searchId) {
        long startTime = System.nanoTime();
        int comparisons = 0;
        int left = 0;
        int right = sortedProducts.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            comparisons++;
            
            int comparison = sortedProducts[mid].getProductId().compareToIgnoreCase(searchId);
            
            if (comparison == 0) {
                long endTime = System.nanoTime();
                return new SearchResult(sortedProducts[mid], comparisons, endTime - startTime, "Binary Search (ID)", true);
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        long endTime = System.nanoTime();
        return new SearchResult(null, comparisons, endTime - startTime, "Binary Search (ID)", false);
    }
    
    /**
     * Binary Search for price range (requires sorted array by price)
     * Time Complexity: O(log n)
     */
    public static List<Product> binarySearchPriceRange(Product[] sortedByPrice, double minPrice, double maxPrice) {
        List<Product> results = new ArrayList<>();
        
        // Find first product with price >= minPrice
        int leftIndex = findFirstGreaterOrEqual(sortedByPrice, minPrice);
        if (leftIndex == -1) return results;
        
        // Find last product with price <= maxPrice
        int rightIndex = findLastLessOrEqual(sortedByPrice, maxPrice);
        if (rightIndex == -1) return results;
        
        // Collect all products in range
        for (int i = leftIndex; i <= rightIndex; i++) {
            results.add(sortedByPrice[i]);
        }
        
        return results;
    }
    
    // Helper method for price range search
    private static int findFirstGreaterOrEqual(Product[] sortedByPrice, double targetPrice) {
        int left = 0, right = sortedByPrice.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (sortedByPrice[mid].getPrice() >= targetPrice) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    // Helper method for price range search
    private static int findLastLessOrEqual(Product[] sortedByPrice, double targetPrice) {
        int left = 0, right = sortedByPrice.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (sortedByPrice[mid].getPrice() <= targetPrice) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Advanced search with multiple criteria
     * Time Complexity: O(n) - linear scan with multiple filters
     */
    public static List<Product> advancedSearch(Product[] products, String nameKeyword, 
                                             String category, double minPrice, double maxPrice, 
                                             double minRating, boolean inStockOnly) {
        List<Product> results = new ArrayList<>();
        
        for (Product product : products) {
            // Check all criteria
            boolean matches = true;
            
            if (nameKeyword != null && !nameKeyword.isEmpty()) {
                matches &= product.getProductName().toLowerCase().contains(nameKeyword.toLowerCase());
            }
            
            if (category != null && !category.isEmpty()) {
                matches &= product.getCategory().equalsIgnoreCase(category);
            }
            
            matches &= product.getPrice() >= minPrice && product.getPrice() <= maxPrice;
            matches &= product.getRating() >= minRating;
            
            if (inStockOnly) {
                matches &= product.isInStock();
            }
            
            if (matches) {
                results.add(product);
            }
        }
        
        return results;
    }
    
    /**
     * Fuzzy search for product names (handles typos and partial matches)
     * Time Complexity: O(n * m) where m is average string length
     */
    public static List<Product> fuzzySearchByName(Product[] products, String searchTerm, int maxDistance) {
        List<Product> results = new ArrayList<>();
        
        for (Product product : products) {
            int distance = calculateLevenshteinDistance(
                product.getProductName().toLowerCase(), 
                searchTerm.toLowerCase()
            );
            
            if (distance <= maxDistance) {
                results.add(product);
            }
        }
        
        // Sort by relevance (distance)
        results.sort((p1, p2) -> {
            int dist1 = calculateLevenshteinDistance(p1.getProductName().toLowerCase(), searchTerm.toLowerCase());
            int dist2 = calculateLevenshteinDistance(p2.getProductName().toLowerCase(), searchTerm.toLowerCase());
            return Integer.compare(dist1, dist2);
        });
        
        return results;
    }
    
    // Helper method for fuzzy search
    private static int calculateLevenshteinDistance(String s1, String s2) {
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
    
    /**
     * Utility method to sort products by different criteria
     */
    public static void sortProducts(Product[] products, String sortBy) {
        switch (sortBy.toLowerCase()) {
            case "name":
                Arrays.sort(products, (p1, p2) -> p1.getProductName().compareToIgnoreCase(p2.getProductName()));
                break;
            case "id":
                Arrays.sort(products, (p1, p2) -> p1.getProductId().compareToIgnoreCase(p2.getProductId()));
                break;
            case "price":
                Arrays.sort(products, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
                break;
            case "rating":
                Arrays.sort(products, (p1, p2) -> Double.compare(p2.getRating(), p1.getRating()));
                break;
            case "category":
                Arrays.sort(products, (p1, p2) -> p1.getCategory().compareToIgnoreCase(p2.getCategory()));
                break;
            default:
                throw new IllegalArgumentException("Invalid sort criteria: " + sortBy);
        }
    }
}