public class WebApp implements Observer {
    private String websiteName;
    private String sessionId;
    
    public WebApp(String websiteName, String sessionId) {
        this.websiteName = websiteName;
        this.sessionId = sessionId;
    }
    
    @Override
    public void update(String stockSymbol, double stockPrice, double changePercent) {
        System.out.println("[WEB APP - " + websiteName + "]");
        System.out.println("  Session: " + sessionId);
        System.out.println("  🌐 Live Update: " + stockSymbol + " = $" + 
                         String.format("%.2f", stockPrice));
        
        String trend = changePercent > 0 ? "↗️ RISING" : 
                      changePercent < 0 ? "↘️ FALLING" : "→ STABLE";
        System.out.println("  Status: " + trend + " (" + 
                         String.format("%.2f", changePercent) + "%)");
        
        // Simulate web-specific actions
        System.out.println("  🔄 Updating chart data...");
        if (Math.abs(changePercent) > 10) {
            System.out.println("  ❗ Trading halt recommendation!");
        }
        System.out.println();
    }
    
    public String getWebsiteName() {
        return websiteName;
    }
    
    public String getSessionId() {
        return sessionId;
    }
}
