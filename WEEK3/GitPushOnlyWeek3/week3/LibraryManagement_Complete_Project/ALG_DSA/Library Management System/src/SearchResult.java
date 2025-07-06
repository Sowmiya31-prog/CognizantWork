import java.util.List;

/**
 * SearchResult class to store search results with performance metrics
 * Helps in analyzing and comparing different search algorithms
 */
public class SearchResult {
    private List<Book> books;
    private int comparisons;
    private long executionTime;
    private String searchType;
    
    // Constructor
    public SearchResult(List<Book> books, int comparisons, long executionTime, String searchType) {
        this.books = books;
        this.comparisons = comparisons;
        this.executionTime = executionTime;
        this.searchType = searchType;
    }
    
    // Getters
    public List<Book> getBooks() { 
        return books; 
    }
    
    public int getComparisons() { 
        return comparisons; 
    }
    
    public long getExecutionTime() { 
        return executionTime; 
    }
    
    public String getSearchType() { 
        return searchType; 
    }
    
    // Setters (if needed for modification)
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    public void setComparisons(int comparisons) {
        this.comparisons = comparisons;
    }
    
    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
    
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    
    // Utility methods
    public boolean hasResults() {
        return books != null && !books.isEmpty();
    }
    
    public int getResultCount() {
        return books != null ? books.size() : 0;
    }
    
    @Override
    public String toString() {
        return String.format("SearchResult[Type: %s, Results: %d, Comparisons: %d, Time: %d ns]",
                           searchType, getResultCount(), comparisons, executionTime);
    }
}