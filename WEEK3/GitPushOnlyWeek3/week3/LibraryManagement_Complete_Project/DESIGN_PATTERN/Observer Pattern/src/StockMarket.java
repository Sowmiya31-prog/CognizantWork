import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete Subject - maintains stock data and observer list
 */
public class StockMarket implements Stock {
    private List<Observer> observers;
    private Map<String, Double> stockPrices;
    private Map<String, Double> previousPrices;
    private String lastUpdatedStock;
    
    public StockMarket() {
        this.observers = new ArrayList<>();
        this.stockPrices = new HashMap<>();
        this.previousPrices = new HashMap<>();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer registered. Total observers: " + observers.size());
    }
    
    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer deregistered. Total observers: " + observers.size());
    }
    
    @Override
    public void notifyObservers() {
        if (lastUpdatedStock != null) {
            double currentPrice = stockPrices.get(lastUpdatedStock);
            double previousPrice = previousPrices.getOrDefault(lastUpdatedStock, currentPrice);
            double changePercent = ((currentPrice - previousPrice) / previousPrice) * 100;
            
            System.out.println("Notifying " + observers.size() + " observers about " + lastUpdatedStock);
            
            for (Observer observer : observers) {
                observer.update(lastUpdatedStock, currentPrice, changePercent);
            }
        }
    }
    
    /**
     * Updates stock price and notifies observers
     */
    public void setStockPrice(String symbol, double price) {
        // Store previous price for change calculation
        if (stockPrices.containsKey(symbol)) {
            previousPrices.put(symbol, stockPrices.get(symbol));
        } else {
            previousPrices.put(symbol, price);
        }
        
        stockPrices.put(symbol, price);
        lastUpdatedStock = symbol;
        
        System.out.println("\n--- Stock Price Update ---");
        System.out.println("Stock: " + symbol + " | New Price: $" + price);
        
        notifyObservers();
    }
    
    /**
     * Gets current stock price
     */
    public double getStockPrice(String symbol) {
        return stockPrices.getOrDefault(symbol, 0.0);
    }
    
    /**
     * Gets all current stock prices
     */
    public Map<String, Double> getAllStockPrices() {
        return new HashMap<>(stockPrices);
    }
}