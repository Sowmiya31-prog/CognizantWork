/**
 * Additional Concrete Strategy - Bank Transfer Payment implementation
 */
public class BankTransferPayment implements PaymentStrategy {
    private String accountNumber;
    private String routingNumber;
    private String accountHolderName;
    
    public BankTransferPayment(String accountNumber, String routingNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.accountHolderName = accountHolderName;
    }
    
    @Override
    public boolean validatePaymentDetails() {
        System.out.println("Validating Bank Transfer details...");
        
        if (accountNumber == null || accountNumber.length() < 8) {
            System.out.println("❌ Invalid account number");
            return false;
        }
        
        if (routingNumber == null || routingNumber.length() != 9) {
            System.out.println("❌ Invalid routing number");
            return false;
        }
        
        if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
            System.out.println("❌ Invalid account holder name");
            return false;
        }
        
        System.out.println("✅ Bank Transfer details validated");
        return true;
    }
    
    @Override
    public boolean pay(double amount) {
        if (!validatePaymentDetails()) {
            return false;
        }
        
        System.out.println("\n--- Bank Transfer Payment Processing ---");
        System.out.println("🏦 Account: ****" + accountNumber.substring(accountNumber.length() - 4));
        System.out.println("👤 Account Holder: " + accountHolderName);
        System.out.println("💰 Amount: $" + String.format("%.2f", amount));
        
        // Simulate bank transfer processing
        System.out.println("🔄 Initiating bank transfer...");
        simulateProcessingDelay(3000);
        
        // Bank transfers are typically more reliable (98% success rate)
        boolean success = Math.random() > 0.02;
        
        if (success) {
            System.out.println("✅ Bank transfer of $" + String.format("%.2f", amount) + " initiated successfully");
            System.out.println("⏰ Transfer will complete in 1-3 business days");
            return true;
        } else {
            System.out.println("❌ Bank transfer failed - Account verification required");
            return false;
        }
    }
    
    @Override
    public String getPaymentType() {
        return "Bank Transfer";
    }
    
    private void simulateProcessingDelay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}