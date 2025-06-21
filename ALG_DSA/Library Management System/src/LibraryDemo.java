/**
 * LibraryDemo - Main class to demonstrate the Library Management System
 * Shows practical examples of Linear Search vs Binary Search
 */
public class LibraryDemo {
    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        
        System.out.println("=== LIBRARY MANAGEMENT SYSTEM DEMO ===\n");
        
        // Adding sample books
        System.out.println("1. ADDING BOOKS TO LIBRARY:");
        System.out.println("-".repeat(40));
        
        library.addBook(new Book(1, "To Kill a Mockingbird", "Harper Lee", "Fiction", 1960));
        library.addBook(new Book(2, "1984", "George Orwell", "Dystopian Fiction", 1949));
        library.addBook(new Book(3, "Pride and Prejudice", "Jane Austen", "Romance", 1813));
        library.addBook(new Book(4, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1925));
        library.addBook(new Book(5, "Animal Farm", "George Orwell", "Political Satire", 1945));
        library.addBook(new Book(6, "Lord of the Flies", "William Golding", "Fiction", 1954));
        library.addBook(new Book(7, "Brave New World", "Aldous Huxley", "Science Fiction", 1932));
        library.addBook(new Book(8, "The Catcher in the Rye", "J.D. Salinger", "Fiction", 1951));
        library.addBook(new Book(9, "Of Mice and Men", "John Steinbeck", "Fiction", 1937));
        library.addBook(new Book(10, "The Hobbit", "J.R.R. Tolkien", "Fantasy", 1937));
        
        // Display all books
        library.displayAllBooks();
        
        // LINEAR SEARCH DEMONSTRATIONS
        System.out.println("\n2. LINEAR SEARCH DEMONSTRATIONS:");
        System.out.println("-".repeat(40));
        
        // Search by title
        SearchResult result1 = library.linearSearchByTitle("1984");
        library.displaySearchResults(result1, "Title: '1984'");
        
        // Search by author
        SearchResult result2 = library.linearSearchByAuthor("George Orwell");
        library.displaySearchResults(result2, "Author: 'George Orwell'");
        
        // BINARY SEARCH DEMONSTRATIONS
        System.out.println("\n3. BINARY SEARCH DEMONSTRATIONS:");
        System.out.println("-".repeat(40));
        
        // Search by title
        SearchResult result3 = library.binarySearchByTitle("The Great Gatsby");
        library.displaySearchResults(result3, "Title: 'The Great Gatsby'");
        
        // Search by author
        SearchResult result4 = library.binarySearchByAuthor("Jane Austen");
        library.displaySearchResults(result4, "Author: 'Jane Austen'");
        
        // PERFORMANCE COMPARISON
        System.out.println("\n4. PERFORMANCE COMPARISON:");
        System.out.println("-".repeat(40));
        library.compareSearchPerformance("Animal Farm");
        library.compareSearchPerformance("Non-existent Book");
        
        // ADVANCED SEARCH
        System.out.println("\n5. ADVANCED SEARCH DEMONSTRATION:");
        System.out.println("-".repeat(40));
        SearchResult advancedResult = library.advancedSearch(null, "George Orwell", null, null);
        library.displaySearchResults(advancedResult, "All books by George Orwell");
        
        // Search by genre
        SearchResult genreResult = library.advancedSearch(null, null, "Fiction", null);
        library.displaySearchResults(genreResult, "All Fiction books");
        
        // ALGORITHM ANALYSIS
        System.out.println("\n6. ALGORITHM ANALYSIS:");
        System.out.println("-".repeat(40));
        printAlgorithmAnalysis();
        
        // INTERACTIVE DEMONSTRATIONS
        System.out.println("\n7. INTERACTIVE DEMONSTRATIONS:");
        System.out.println("-".repeat(40));
        demonstrateWorstCase(library);
        demonstrateBestCase(library);
    }
    
    /**
     * Print detailed algorithm analysis
     */
    private static void printAlgorithmAnalysis() {
        System.out.println("LINEAR SEARCH:");
        System.out.println("• Time Complexity: O(n)");
        System.out.println("• Space Complexity: O(1)");
        System.out.println("• Works on unsorted data");
        System.out.println("• Best for small datasets or unsorted data");
        System.out.println("• Guaranteed to find all matches");
        
        System.out.println("\nBINARY SEARCH:");
        System.out.println("• Time Complexity: O(log n)");
        System.out.println("• Space Complexity: O(1)");
        System.out.println("• Requires sorted data");
        System.out.println("• Best for large, sorted datasets");
        System.out.println("• Much faster for large datasets");
        
        System.out.println("\nWHEN TO USE EACH:");
        System.out.println("Linear Search:");
        System.out.println("• Small datasets (< 100 items)");
        System.out.println("• Unsorted data");
        System.out.println("• When you need to find all occurrences");
        System.out.println("• When sorting cost is too high");
        
        System.out.println("\nBinary Search:");
        System.out.println("• Large datasets (> 1000 items)");
        System.out.println("• When data is already sorted");
        System.out.println("• When search operations are frequent");
        System.out.println("• When performance is critical");
    }
    
    /**
     * Demonstrate worst-case scenarios
     */
    private static void demonstrateWorstCase(LibraryManagementSystem library) {
        System.out.println("\nWORST CASE SCENARIOS:");
        System.out.println("• Linear Search: Target is the last element or doesn't exist");
        System.out.println("• Binary Search: Requires maximum log(n) comparisons");
        
        // Demonstrate searching for non-existent book (worst case for linear)
        SearchResult worstLinear = library.linearSearchByTitle("Non-existent Book");
        SearchResult worstBinary = library.binarySearchByTitle("Non-existent Book");
        
        System.out.println("\nSearching for 'Non-existent Book':");
        System.out.printf("Linear Search: %d comparisons%n", worstLinear.getComparisons());
        System.out.printf("Binary Search: %d comparisons%n", worstBinary.getComparisons());
    }
    
    /**
     * Demonstrate best-case scenarios
     */
    private static void demonstrateBestCase(LibraryManagementSystem library) {
        System.out.println("\nBEST CASE SCENARIOS:");
        System.out.println("• Linear Search: Target is the first element");
        System.out.println("• Binary Search: Target is the middle element");
        
        // For demonstration, we'll search for the first book added
        SearchResult bestLinear = library.linearSearchByTitle("The Hobbit"); // Last added, first in list
        SearchResult bestBinary = library.binarySearchByTitle("The Hobbit");
        
        System.out.println("\nSearching for 'The Hobbit' (first in unsorted list):");
        System.out.printf("Linear Search: %d comparisons%n", bestLinear.getComparisons());
        System.out.printf("Binary Search: %d comparisons%n", bestBinary.getComparisons());
    }
    
    /**
     * Additional utility method to demonstrate efficiency with larger datasets
     */
    public static void demonstrateScalability() {
        System.out.println("\n=== SCALABILITY DEMONSTRATION ===");
        System.out.println("Dataset Size | Linear Search | Binary Search | Efficiency Gain");
        System.out.println("-------------|---------------|---------------|----------------");
        
        int[] sizes = {10, 100, 1000, 10000, 100000};
        for (int size : sizes) {
            int linearComparisons = size; // Worst case for linear
            int binaryComparisons = (int) Math.ceil(Math.log(size) / Math.log(2)); // Worst case for binary
            double efficiency = (double) linearComparisons / binaryComparisons;
            
            System.out.printf("%-12d | %-13d | %-13d | %.1fx%n", 
                            size, linearComparisons, binaryComparisons, efficiency);
        }
    }
}