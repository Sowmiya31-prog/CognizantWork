public class ImageViewerApp {
    public static void main(String[] args) {
        System.out.println("=== Proxy Pattern Example - Image Viewer ===\n");
        
        // Create proxy images (no actual loading happens here)
        System.out.println("1. Creating proxy images...");
        Image image1 = new ProxyImage("vacation_photo.jpg");
        Image image2 = new ProxyImage("family_portrait.png");
        Image image3 = new ProxyImage("landscape.gif");
        
        System.out.println("Proxy images created instantly (no loading yet)\n");
        
        // First display - triggers lazy loading
        System.out.println("2. First display of vacation_photo.jpg:");
        image1.display();
        System.out.println();
        
        // Second display - uses cached image
        System.out.println("3. Second display of vacation_photo.jpg (should be instant):");
        image1.display();
        System.out.println();
        
        // Display other images
        System.out.println("4. First display of family_portrait.png:");
        image2.display();
        System.out.println();
        
        System.out.println("5. First display of landscape.gif:");
        image3.display();
        System.out.println();
        
        // Display cached images again
        System.out.println("6. Displaying cached images:");
        image1.display();
        System.out.println();
        image2.display();
        System.out.println();
        image3.display();
        System.out.println();
        
        // Demonstrate proxy status
        demonstrateProxyStatus();
    }
    
    /**
     * Additional demonstration of proxy functionality
     */
    private static void demonstrateProxyStatus() {
        System.out.println("7. Demonstrating proxy status:");
        
        ProxyImage proxyImage = new ProxyImage("new_image.jpg");
        System.out.println("Created proxy for: " + proxyImage.getFilename());
        System.out.println("Is image loaded? " + proxyImage.isLoaded());
        
        System.out.println("\nDisplaying image for first time:");
        proxyImage.display();
        System.out.println("Is image loaded now? " + proxyImage.isLoaded());
        
        System.out.println("\nDisplaying same image again:");
        proxyImage.display();
    }
}