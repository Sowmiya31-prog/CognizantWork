public class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromServer();
    }
    
    /**
     * Simulates loading an image from a remote server
     * This is an expensive operation that we want to delay
     */
    private void loadImageFromServer() {
        System.out.println("Loading image from server: " + filename);
        // Simulate network delay
        try {
            Thread.sleep(2000); // 2 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Image loaded successfully: " + filename);
    }
    
    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}