/**
 * Strategy Interface - defines the common interface for all payment strategies
 */
public interface PaymentStrategy {
    boolean pay(double amount);
    String getPaymentType();
    boolean validatePaymentDetails();
}