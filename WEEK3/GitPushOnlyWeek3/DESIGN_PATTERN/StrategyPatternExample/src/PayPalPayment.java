/**
 * Concrete Strategy - PayPal Payment implementation
 */
public class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;
    
    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public boolean validatePaymentDetails() {
        System.out.println("Validating PayPal credentials...");
        
        // Basic validation logic
        if (email == null || !email.contains("@") || !email.contains(".")) {
            System.out.println("❌ Invalid email address");
            return false;
        }
        
        if (password == null || password.length() < 6) {
            System.out.println("❌ Invalid password");
            return false;
        }
        
        System.out.println("✅ PayPal credentials validated");
        return true;
    }
    
    @Override
    public boolean pay(double amount) {
        if (!validatePaymentDetails()) {
            return false;
        }
        
        System.out.println("\n--- PayPal Payment Processing ---");
        System.out.println("📧 PayPal Account: " + maskEmail(email));
        System.out.println("💰 Amount: $" + String.format("%.2f", amount));
        
        // Simulate PayPal authentication and processing
        System.out.println("🔐 Authenticating with PayPal...");
        simulateProcessingDelay(1500);
        
        System.out.println("💸 Processing payment through PayPal...");
        simulateProcessingDelay(1000);
        
        // Simulate random success/failure (95% success rate for PayPal)
        boolean success = Math.random() > 0.05;
        
        if (success) {
            System.out.println("✅ PayPal payment of $" + String.format("%.2f", amount) + " completed successfully");
            System.out.println("📱 PayPal notification sent to mobile app");
            return true;
        } else {
            System.out.println("❌ PayPal payment failed - Account temporarily unavailable");
            return false;
        }
    }
    
    @Override
    public String getPaymentType() {
        return "PayPal";
    }
    
    private String maskEmail(String email) {
        int atIndex = email.indexOf("@");
        if (atIndex > 2) {
            return email.substring(0, 2) + "***" + email.substring(atIndex);
        }
        return "***" + email.substring(atIndex);
    }
    
    private void simulateProcessingDelay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}