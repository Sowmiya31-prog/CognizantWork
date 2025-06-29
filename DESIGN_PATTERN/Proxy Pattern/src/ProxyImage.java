public class ProxyImage implements Image {
    private String filename;
    private RealImage realImage;
    private boolean isImageLoaded;
    
    public ProxyImage(String filename) {
        this.filename = filename;
        this.isImageLoaded = false;
    }
    
    @Override
    public void display() {
        // Lazy initialization - create RealImage only when needed
        if (!isImageLoaded) {
            System.out.println("ProxyImage: First time accessing " + filename);
            realImage = new RealImage(filename);
            isImageLoaded = true;
        } else {
            System.out.println("ProxyImage: Using cached image " + filename);
        }
        
        // Delegate to the real image
        realImage.display();
    }
    
    /**
     * Additional method to check if image is loaded (for demonstration)
     */
    public boolean isLoaded() {
        return isImageLoaded;
    }
    
    public String getFilename() {
        return filename;
    }
}
