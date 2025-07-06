import java.util.Objects;

/**
 * Book class represents a book in the library system
 * Contains all book attributes and basic operations
 */
public class Book {
    private int bookId;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private boolean isAvailable;
    
    // Constructor
    public Book(int bookId, String title, String author, String genre, int publicationYear) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
    }
    
    // Getters and Setters
    public int getBookId() {
        return bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public int getPublicationYear() {
        return publicationYear;
    }
    
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    @Override
    public String toString() {
        return String.format("Book[ID: %d, Title: '%s', Author: '%s', Genre: '%s', Year: %d, Available: %s]",
                           bookId, title, author, genre, publicationYear, isAvailable ? "Yes" : "No");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return bookId == book.bookId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }
}