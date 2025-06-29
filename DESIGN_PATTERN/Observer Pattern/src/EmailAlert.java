public class EmailAlert implements Observer {
    private String emailAddress;
    private double alertThreshold;
    
    public EmailAlert(String emailAddress, double alertThreshold) {
        this.emailAddress = emailAddress;
        this.alertThreshold = alertThreshold;
    }
    
    @Override
    public void update(String stockSymbol, double stockPrice, double changePercent) {
        // Only send email if change exceeds threshold
        if (Math.abs(changePercent) >= alertThreshold) {
            System.out.println("[EMAIL ALERT]");
            System.out.println("  To: " + emailAddress);
            System.out.println("  📧 Subject: Stock Alert - " + stockSymbol);
            System.out.println("  📊 Price: $" + String.format("%.2f", stockPrice));
            System.out.println("  📈 Change: " + String.format("%.2f", changePercent) + "%");
            System.out.println("  ⚠️  Alert triggered (threshold: " + alertThreshold + "%)");
            System.out.println();
        }
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public double getAlertThreshold() {
        return alertThreshold;
    }
}