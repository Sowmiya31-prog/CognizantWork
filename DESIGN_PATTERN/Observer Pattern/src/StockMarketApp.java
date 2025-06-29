import java.util.Map;

public class StockMarketApp {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Example - Stock Market Monitor ===\n");
        
        // Create the subject (stock market)
        StockMarket stockMarket = new StockMarket();
        
        // Create observers
        System.out.println("1. Creating observers...");
        MobileApp mobileApp1 = new MobileApp("StockTrader Pro", "user123");
        MobileApp mobileApp2 = new MobileApp("InvestMobile", "investor456");
        WebApp webApp1 = new WebApp("TradingPortal.com", "session789");
        WebApp webApp2 = new WebApp("MarketWatch.net", "session101");
        EmailAlert emailAlert = new EmailAlert("trader@example.com", 5.0);
        
        // Register observers
        System.out.println("\n2. Registering observers...");
        stockMarket.registerObserver(mobileApp1);
        stockMarket.registerObserver(webApp1);
        stockMarket.registerObserver(emailAlert);
        
        // Test stock price updates
        System.out.println("\n3. Testing stock price updates...");
        stockMarket.setStockPrice("AAPL", 150.00);
        
        // Add more observers
        System.out.println("\n4. Adding more observers...");
        stockMarket.registerObserver(mobileApp2);
        stockMarket.registerObserver(webApp2);
        
        // More price updates
        stockMarket.setStockPrice("GOOGL", 2500.00);
        stockMarket.setStockPrice("AAPL", 155.50); // 3.67% increase
        
        // Test significant price change (triggers email alert)
        stockMarket.setStockPrice("TSLA", 800.00);
        stockMarket.setStockPrice("TSLA", 720.00); // 10% decrease
        
        // Test observer deregistration
        System.out.println("\n5. Testing observer deregistration...");
        stockMarket.deregisterObserver(mobileApp1);
        stockMarket.deregisterObserver(emailAlert);
        
        stockMarket.setStockPrice("GOOGL", 2450.00); // 2% decrease
        
        // Final stock update with fewer observers
        stockMarket.setStockPrice("AAPL", 160.00); // 2.9% increase
        
        // Display final summary
        System.out.println("\n6. Final Stock Summary:");
        displayStockSummary(stockMarket);
    }
    
    /**
     * Helper method to display current stock prices
     */
    private static void displayStockSummary(StockMarket stockMarket) {
        System.out.println("Current Stock Prices:");
        for (Map.Entry<String, Double> entry : stockMarket.getAllStockPrices().entrySet()) {
            System.out.println("  " + entry.getKey() + ": $" + String.format("%.2f", entry.getValue()));
        }
    }
}
