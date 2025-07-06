import java.util.*;

/**
 * LibraryManagementSystem class implements the core functionality
 * Includes both Linear Search and Binary Search algorithms
 */
public class LibraryManagementSystem {
    private List<Book> books;
    private List<Book> sortedBooksByTitle;
    private List<Book> sortedBooksByAuthor;
    
    public LibraryManagementSystem() {
        this.books = new ArrayList<>();
        this.sortedBooksByTitle = new ArrayList<>();
        this.sortedBooksByAuthor = new ArrayList<>();
    }
    
    /**
     * Add a book to the library
     */
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
        updateSortedLists();
    }
    
    /**
     * Remove a book from the library
     */
    public boolean removeBook(int bookId) {
        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                bookToRemove = book;
                break;
            }
        }
        
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            updateSortedLists();
            System.out.println("Book removed: " + bookToRemove.getTitle());
            return true;
        }
        
        System.out.println("Book with ID " + bookId + " not found!");
        return false;
    }
    
    /**
     * Update sorted lists after modifications
     */
    private void updateSortedLists() {
        // Sort by title
        sortedBooksByTitle = new ArrayList<>(books);
        sortedBooksByTitle.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
        
        // Sort by author
        sortedBooksByAuthor = new ArrayList<>(books);
        sortedBooksByAuthor.sort((b1, b2) -> b1.getAuthor().compareToIgnoreCase(b2.getAuthor()));
    }
    
    /**
     * LINEAR SEARCH - Find books by title
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public SearchResult linearSearchByTitle(String title) {
        long startTime = System.nanoTime();
        List<Book> results = new ArrayList<>();
        int comparisons = 0;
        
        for (Book book : books) {
            comparisons++;
            if (book.getTitle().equalsIgnoreCase(title)) {
                results.add(book);
            }
        }
        
        long endTime = System.nanoTime();
        return new SearchResult(results, comparisons, endTime - startTime, "Linear Search");
    }
    
    /**
     * LINEAR SEARCH - Find books by author
     * Time Complexity: O(n)
     */
    public SearchResult linearSearchByAuthor(String author) {
        long startTime = System.nanoTime();
        List<Book> results = new ArrayList<>();
        int comparisons = 0;
        
        for (Book book : books) {
            comparisons++;
            if (book.getAuthor().equalsIgnoreCase(author)) {
                results.add(book);
            }
        }
        
        long endTime = System.nanoTime();
        return new SearchResult(results, comparisons, endTime - startTime, "Linear Search");
    }
    
    /**
     * BINARY SEARCH - Find book by exact title (sorted list required)
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public SearchResult binarySearchByTitle(String title) {
        long startTime = System.nanoTime();
        List<Book> results = new ArrayList<>();
        int comparisons = 0;
        
        int left = 0;
        int right = sortedBooksByTitle.size() - 1;
        
        while (left <= right) {
            comparisons++;
            int mid = left + (right - left) / 2;
            Book midBook = sortedBooksByTitle.get(mid);
            int comparison = midBook.getTitle().compareToIgnoreCase(title);
            
            if (comparison == 0) {
                results.add(midBook);
                
                // Find all books with the same title (in case of duplicates)
                // Check left side
                int leftIndex = mid - 1;
                while (leftIndex >= 0 && 
                       sortedBooksByTitle.get(leftIndex).getTitle().equalsIgnoreCase(title)) {
                    results.add(sortedBooksByTitle.get(leftIndex));
                    leftIndex--;
                    comparisons++;
                }
                
                // Check right side
                int rightIndex = mid + 1;
                while (rightIndex < sortedBooksByTitle.size() && 
                       sortedBooksByTitle.get(rightIndex).getTitle().equalsIgnoreCase(title)) {
                    results.add(sortedBooksByTitle.get(rightIndex));
                    rightIndex++;
                    comparisons++;
                }
                
                break;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        long endTime = System.nanoTime();
        return new SearchResult(results, comparisons, endTime - startTime, "Binary Search");
    }
    
    /**
     * BINARY SEARCH - Find books by author
     */
    public SearchResult binarySearchByAuthor(String author) {
        long startTime = System.nanoTime();
        List<Book> results = new ArrayList<>();
        int comparisons = 0;
        
        int left = 0;
        int right = sortedBooksByAuthor.size() - 1;
        int foundIndex = -1;
        
        // Find any occurrence first
        while (left <= right) {
            comparisons++;
            int mid = left + (right - left) / 2;
            Book midBook = sortedBooksByAuthor.get(mid);
            int comparison = midBook.getAuthor().compareToIgnoreCase(author);
            
            if (comparison == 0) {
                foundIndex = mid;
                break;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // If found, collect all books by this author
        if (foundIndex != -1) {
            // Add the found book
            results.add(sortedBooksByAuthor.get(foundIndex));
            
            // Find all books by the same author (left side)
            int leftIndex = foundIndex - 1;
            while (leftIndex >= 0 && 
                   sortedBooksByAuthor.get(leftIndex).getAuthor().equalsIgnoreCase(author)) {
                results.add(sortedBooksByAuthor.get(leftIndex));
                leftIndex--;
                comparisons++;
            }
            
            // Find all books by the same author (right side)
            int rightIndex = foundIndex + 1;
            while (rightIndex < sortedBooksByAuthor.size() && 
                   sortedBooksByAuthor.get(rightIndex).getAuthor().equalsIgnoreCase(author)) {
                results.add(sortedBooksByAuthor.get(rightIndex));
                rightIndex++;
                comparisons++;
            }
        }
        
        long endTime = System.nanoTime();
        return new SearchResult(results, comparisons, endTime - startTime, "Binary Search");
    }
    
    /**
     * Advanced search with multiple criteria
     */
    public SearchResult advancedSearch(String title, String author, String genre, Integer year) {
        long startTime = System.nanoTime();
        List<Book> results = new ArrayList<>();
        int comparisons = 0;
        
        for (Book book : books) {
            comparisons++;
            boolean matches = true;
            
            if (title != null && !book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matches = false;
            }
            if (author != null && !book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                matches = false;
            }
            if (genre != null && !book.getGenre().equalsIgnoreCase(genre)) {
                matches = false;
            }
            if (year != null && book.getPublicationYear() != year) {
                matches = false;
            }
            
            if (matches) {
                results.add(book);
            }
        }
        
        long endTime = System.nanoTime();
        return new SearchResult(results, comparisons, endTime - startTime, "Advanced Search");
    }
    
    /**
     * Display search results with performance metrics
     */
    public void displaySearchResults(SearchResult result, String searchQuery) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SEARCH RESULTS");
        System.out.println("=".repeat(60));
        System.out.println("Search Type: " + result.getSearchType());
        System.out.println("Query: " + searchQuery);
        System.out.println("Results Found: " + result.getBooks().size());
        System.out.println("Comparisons Made: " + result.getComparisons());
        System.out.println("Execution Time: " + result.getExecutionTime() + " nanoseconds");
        System.out.println("-".repeat(60));
        
        if (result.getBooks().isEmpty()) {
            System.out.println("No books found matching the search criteria.");
        } else {
            for (int i = 0; i < result.getBooks().size(); i++) {
                System.out.println((i + 1) + ". " + result.getBooks().get(i));
            }
        }
        System.out.println("=".repeat(60));
    }
    
    /**
     * Display all books in the library
     */
    public void displayAllBooks() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ALL BOOKS IN LIBRARY");
        System.out.println("=".repeat(60));
        
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i));
            }
        }
        System.out.println("Total Books: " + books.size());
        System.out.println("=".repeat(60));
    }
    
    /**
     * Performance comparison between linear and binary search
     */
    public void compareSearchPerformance(String searchTitle) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PERFORMANCE COMPARISON");
        System.out.println("=".repeat(60));
        System.out.println("Searching for: '" + searchTitle + "'");
        System.out.println("-".repeat(60));
        
        // Linear Search
        SearchResult linearResult = linearSearchByTitle(searchTitle);
        System.out.printf("%-15s | %-12s | %-15s | %-10s%n", 
                         "Search Type", "Comparisons", "Time (ns)", "Results");
        System.out.println("-".repeat(60));
        System.out.printf("%-15s | %-12d | %-15d | %-10d%n",
                         "Linear", linearResult.getComparisons(), 
                         linearResult.getExecutionTime(), linearResult.getBooks().size());
        
        // Binary Search
        SearchResult binaryResult = binarySearchByTitle(searchTitle);
        System.out.printf("%-15s | %-12d | %-15d | %-10d%n",
                         "Binary", binaryResult.getComparisons(), 
                         binaryResult.getExecutionTime(), binaryResult.getBooks().size());
        
        // Analysis
        System.out.println("-".repeat(60));
        if (linearResult.getComparisons() > 0 && binaryResult.getComparisons() > 0) {
            double efficiencyRatio = (double) linearResult.getComparisons() / binaryResult.getComparisons();
            System.out.printf("Binary search was %.2fx more efficient in comparisons%n", efficiencyRatio);
        }
        System.out.println("=".repeat(60));
    }
    
    // Getters
    public List<Book> getBooks() { return new ArrayList<>(books); }
    public int getTotalBooks() { return books.size(); }
}