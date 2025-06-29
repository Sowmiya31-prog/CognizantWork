/**
 * Context Class - manages the payment strategy and executes payments
 */
public class PaymentContext {
    private PaymentStrategy paymentStrategy;
    private double totalAmount;
    private String orderId;
    
    public PaymentContext(String orderId) {
        this.orderId = orderId;
        this.totalAmount = 0.0;
    }
    
    /**
     * Sets the payment strategy to be used
     */
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        System.out.println("🔄 Payment method changed to: " + paymentStrategy.getPaymentType());
    }
    
    /**
     * Sets the amount to be paid
     */
    public void setAmount(double amount) {
        this.totalAmount = amount;
    }
    
    /**
     * Executes the payment using the current strategy
     */
    public boolean executePayment() {
        if (paymentStrategy == null) {
            System.out.println("❌ No payment method selected");
            return false;
        }
        
        if (totalAmount <= 0) {
            System.out.println("❌ Invalid payment amount");
            return false;
        }
        
        System.out.println("\n🛒 Processing Order: " + orderId);
        System.out.println("💳 Payment Method: " + paymentStrategy.getPaymentType());
        
        boolean result = paymentStrategy.pay(totalAmount);
        
        if (result) {
            System.out.println("🎉 Order " + orderId + " payment completed successfully!");
        } else {
            System.out.println("💔 Order " + orderId + " payment failed. Please try again.");
        }
        
        return result;
    }
    
    /**
     * Gets the current payment method type
     */
    public String getCurrentPaymentMethod() {
        return paymentStrategy != null ? paymentStrategy.getPaymentType() : "None";
    }
    
    /**
     * Gets the total amount
     */
    public double getTotalAmount() {
        return totalAmount;
    }
    
    /**
     * Gets the order ID
     */
    public String getOrderId() {
        return orderId;
    }
}