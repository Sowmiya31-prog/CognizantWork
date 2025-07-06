import java.util.Scanner;

/**
 * Test class to demonstrate the Strategy Pattern implementation
 */
public class PaymentSystemApp {
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Example - Payment System ===\n");
        
        // Create payment context
        PaymentContext paymentContext = new PaymentContext("ORD-12345");
        paymentContext.setAmount(299.99);
        
        // Demonstrate different payment strategies
        demonstrateAutomaticPayments(paymentContext);
        
        // Interactive demo
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Interactive Payment Demo");
        System.out.println("=".repeat(50));
        interactivePaymentDemo();
    }
    
    /**
     * Demonstrates automatic payment processing with different strategies
     */
    private static void demonstrateAutomaticPayments(PaymentContext context) {
        System.out.println("1. Testing Credit Card Payment:");
        System.out.println("-".repeat(40));
        
        // Create and test credit card payment
        PaymentStrategy creditCard = new CreditCardPayment(
            "1234567890123456", 
            "John Doe", 
            "12/25", 
            "123"
        );
        
        context.setPaymentStrategy(creditCard);
        context.executePayment();
        
        System.out.println("\n2. Testing PayPal Payment:");
        System.out.println("-".repeat(40));
        
        // Create and test PayPal payment
        PaymentStrategy paypal = new PayPalPayment(
            "john.doe@example.com", 
            "securepassword123"
        );
        
        context.setPaymentStrategy(paypal);
        context.executePayment();
        
        System.out.println("\n3. Testing Bank Transfer Payment:");
        System.out.println("-".repeat(40));
        
        // Create and test bank transfer payment
        PaymentStrategy bankTransfer = new BankTransferPayment(
            "12345678901", 
            "123456789", 
            "John Doe"
        );
        
        context.setPaymentStrategy(bankTransfer);
        context.executePayment();
    }
    
    /**
     * Interactive demo allowing user to select payment method
     */
    private static void interactivePaymentDemo() {
        Scanner scanner = new Scanner(System.in);
        PaymentContext context = new PaymentContext("ORD-" + System.currentTimeMillis());
        
        // Get amount from user
        System.out.print("Enter payment amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        context.setAmount(amount);
        
        while (true) {
            System.out.println("\nSelect Payment Method:");
            System.out.println("1. Credit Card");
            System.out.println("2. PayPal");
            System.out.println("3. Bank Transfer");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            PaymentStrategy strategy = null;
            
            switch (choice) {
                case 1:
                    strategy = createCreditCardStrategy(scanner);
                    break;
                case 2:
                    strategy = createPayPalStrategy(scanner);
                    break;
                case 3:
                    strategy = createBankTransferStrategy(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using our payment system!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }
            
            if (strategy != null) {
                context.setPaymentStrategy(strategy);
                boolean success = context.executePayment();
                
                if (success) {
                    System.out.println("\n🎊 Transaction completed successfully!");
                    break;
                } else {
                    System.out.println("\n🔄 Would you like to try a different payment method?");
                }
            }
        }
        
        scanner.close();
    }
    
    private static PaymentStrategy createCreditCardStrategy(Scanner scanner) {
        System.out.print("Enter card number (16 digits): ");
        String cardNumber = scanner.nextLine();
        System.out.print("Enter cardholder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter expiry date (MM/YY): ");
        String expiry = scanner.nextLine();
        System.out.print("Enter CVV (3 digits): ");
        String cvv = scanner.nextLine();
        
        return new CreditCardPayment(cardNumber, name, expiry, cvv);
    }
    
    private static PaymentStrategy createPayPalStrategy(Scanner scanner) {
        System.out.print("Enter PayPal email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        return new PayPalPayment(email, password);
    }
    
    private static PaymentStrategy createBankTransferStrategy(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter routing number (9 digits): ");
        String routingNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolder = scanner.nextLine();
        
        return new BankTransferPayment(accountNumber, routingNumber, accountHolder);
    }
}