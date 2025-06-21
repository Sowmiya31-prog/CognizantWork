public class MobileApp implements Observer {
    private String appName;
    private String userId;
    
    public MobileApp(String appName, String userId) {
        this.appName = appName;
        this.userId = userId;
    }
    
    @Override
    public void update(String stockSymbol, double stockPrice, double changePercent) {
        System.out.println("[MOBILE APP - " + appName + "]");
        System.out.println("  User: " + userId);
        System.out.println("  📱 Push Notification: " + stockSymbol + " is now $" + String.format("%.2f", stockPrice));
        
        if (changePercent > 0) {
            System.out.println("  📈 UP " + String.format("%.2f", changePercent) + "%");
        } else if (changePercent < 0) {
            System.out.println("  📉 DOWN " + String.format("%.2f", Math.abs(changePercent)) + "%");
        } else {
            System.out.println("  ➡️ No change");
        }
        
        // Simulate additional mobile-specific actions
        if (Math.abs(changePercent) > 5) {
            System.out.println("  🚨 ALERT: Significant price movement detected!");
        }
        System.out.println();
    }
    
    public String getAppName() {
        return appName;
    }
    
    public String getUserId() {
        return userId;
    }
}