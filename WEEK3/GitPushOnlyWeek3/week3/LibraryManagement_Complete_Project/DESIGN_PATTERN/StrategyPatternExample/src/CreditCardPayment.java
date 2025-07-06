/**
 * Concrete Strategy - Credit Card Payment implementation
 */
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    
    public CreditCardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    
    @Override
    public boolean validatePaymentDetails() {
        System.out.println("Validating Credit Card details...");
        
        // Basic validation logic
        if (cardNumber == null || cardNumber.length() != 16) {
            System.out.println("❌ Invalid card number");
            return false;
        }
        
        if (cardHolderName == null || cardHolderName.trim().isEmpty()) {
            System.out.println("❌ Invalid cardholder name");
            return false;
        }
        
        if (cvv == null || cvv.length() != 3) {
            System.out.println("❌ Invalid CVV");
            return false;
        }
        
        System.out.println("✅ Credit Card details validated");
        return true;
    }
    
    @Override
    public boolean pay(double amount) {
        if (!validatePaymentDetails()) {
            return false;
        }
        
        System.out.println("\n--- Credit Card Payment Processing ---");
        System.out.println("💳 Card Number: ****-****-****-" + cardNumber.substring(12));
        System.out.println("👤 Cardholder: " + cardHolderName);
        System.out.println("💰 Amount: $" + String.format("%.2f", amount));
        
        // Simulate payment processing
        System.out.println("🔄 Contacting bank for authorization...");
        simulateProcessingDelay(2000);
        
        // Simulate random success/failure (90% success rate)
        boolean success = Math.random() > 0.1;
        
        if (success) {
            System.out.println("✅ Payment of $" + String.format("%.2f", amount) + " processed successfully");
            System.out.println("📧 Receipt sent to email");
            return true;
        } else {
            System.out.println("❌ Payment declined - Insufficient funds or card blocked");
            return false;
        }
    }
    
    @Override
    public String getPaymentType() {
        return "Credit Card";
    }
    
    private void simulateProcessingDelay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // Getter methods
    public String getMaskedCardNumber() {
        return "****-****-****-" + cardNumber.substring(12);
    }
}