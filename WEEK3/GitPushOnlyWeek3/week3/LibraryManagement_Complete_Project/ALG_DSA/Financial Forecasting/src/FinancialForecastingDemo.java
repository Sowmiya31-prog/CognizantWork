import java.util.*;
import java.text.DecimalFormat;

/**
 * Financial Forecasting System using Recursive Algorithms
 * 
 * This system demonstrates various recursive approaches for financial predictions
 * including compound interest, investment growth, and trend analysis.
 */

// Class to represent financial data points
class FinancialDataPoint {
    private int period;
    private double value;
    private double growthRate;
    
    public FinancialDataPoint(int period, double value, double growthRate) {
        this.period = period;
        this.value = value;
        this.growthRate = growthRate;
    }
    
    // Getters
    public int getPeriod() { return period; }
    public double getValue() { return value; }
    public double getGrowthRate() { return growthRate; }
    
    @Override
    public String toString() {
        return String.format("Period %d: $%.2f (Growth: %.2f%%)", 
                           period, value, growthRate * 100);
    }
}

// Main Financial Forecasting System
class FinancialForecastingSystem {
    private static final DecimalFormat df = new DecimalFormat("#,##0.00");
    private Map<String, Double> memoizationCache = new HashMap<>();
    private int recursiveCallCount = 0;
    private int memoizedCallCount = 0;
    
    /**
     * BASIC RECURSIVE COMPOUND INTEREST CALCULATION
     * Formula: FV = PV * (1 + r)^n
     * Time Complexity: O(n) - Linear recursion
     * Space Complexity: O(n) - Due to call stack
     */
    public double calculateCompoundInterestRecursive(double principal, double rate, int years) {
        recursiveCallCount++;
        
        // Base case: no more years
        if (years == 0) {
            return principal;
        }
        
        // Recursive case: calculate for (years - 1) and apply interest
        return calculateCompoundInterestRecursive(principal * (1 + rate), rate, years - 1);
    }
    
    /**
     * OPTIMIZED RECURSIVE COMPOUND INTEREST WITH MEMOIZATION
     * Uses memoization to avoid recalculating same values
     * Time Complexity: O(n) - Each subproblem solved once
     * Space Complexity: O(n) - Cache storage + call stack
     */
    public double calculateCompoundInterestMemoized(double principal, double rate, int years) {
        String key = principal + "," + rate + "," + years;
        
        if (memoizationCache.containsKey(key)) {
            return memoizationCache.get(key);
        }
        
        memoizedCallCount++;
        
        // Base case
        if (years == 0) {
            memoizationCache.put(key, principal);
            return principal;
        }
        
        // Recursive case with memoization
        double result = calculateCompoundInterestMemoized(principal * (1 + rate), rate, years - 1);
        memoizationCache.put(key, result);
        return result;
    }
    
    /**
     * FIBONACCI-BASED GROWTH PREDICTION
     * Models growth patterns that follow Fibonacci-like sequences
     * Time Complexity: O(2^n) - Exponential without memoization
     * Space Complexity: O(n) - Call stack depth
     */
    public double fibonacciGrowthPrediction(double baseValue, int period) {
        recursiveCallCount++;
        
        // Base cases
        if (period <= 0) return baseValue;
        if (period == 1) return baseValue * 1.1; // 10% growth
        if (period == 2) return baseValue * 1.2; // 20% growth
        
        // Recursive case: growth follows Fibonacci pattern
        return fibonacciGrowthPrediction(baseValue, period - 1) * 0.6 + 
               fibonacciGrowthPrediction(baseValue, period - 2) * 0.4;
    }
    
    /**
     * OPTIMIZED FIBONACCI GROWTH WITH MEMOIZATION
     * Time Complexity: O(n) - Each subproblem solved once
     * Space Complexity: O(n) - Cache storage
     */
    private Map<Integer, Double> fibCache = new HashMap<>();
    
    public double fibonacciGrowthMemoized(double baseValue, int period) {
        if (fibCache.containsKey(period)) {
            return fibCache.get(period);
        }
        
        memoizedCallCount++;
        
        double result;
        if (period <= 0) {
            result = baseValue;
        } else if (period == 1) {
            result = baseValue * 1.1;
        } else if (period == 2) {
            result = baseValue * 1.2;
        } else {
            result = fibonacciGrowthMemoized(baseValue, period - 1) * 0.6 + 
                    fibonacciGrowthMemoized(baseValue, period - 2) * 0.4;
        }
        
        fibCache.put(period, result);
        return result;
    }
    
    /**
     * RECURSIVE TREND ANALYSIS
     * Analyzes historical data to predict future trends
     * Time Complexity: O(n) - Linear recursion
     * Space Complexity: O(n) - Call stack
     */
    public double predictFutureTrend(double[] historicalData, int currentIndex, double trendFactor, int periodsAhead) {
        recursiveCallCount++;
        
        // Base case: no more periods to predict
        if (periodsAhead == 0) {
            return historicalData[currentIndex];
        }
        
        // Calculate growth rate from historical data
        double growthRate = 0;
        if (currentIndex > 0) {
            growthRate = (historicalData[currentIndex] - historicalData[currentIndex - 1]) 
                        / historicalData[currentIndex - 1];
        }
        
        // Apply trend factor and recurse
        double nextValue = historicalData[currentIndex] * (1 + growthRate * trendFactor);
        
        // Create new array with predicted value
        double[] newData = Arrays.copyOf(historicalData, historicalData.length + 1);
        newData[newData.length - 1] = nextValue;
        
        return predictFutureTrend(newData, newData.length - 1, trendFactor, periodsAhead - 1);
    }
    
    /**
     * RECURSIVE INVESTMENT PORTFOLIO GROWTH
     * Models portfolio growth with varying investment amounts
     * Time Complexity: O(n) - Linear recursion
     * Space Complexity: O(n) - Call stack
     */
    public double calculatePortfolioGrowth(double currentValue, double monthlyInvestment ,double monthlyReturn, int monthsRemaining) {
        recursiveCallCount++;
        
        // Base case: no more months
        if (monthsRemaining == 0) {
            return currentValue;
        }
        
        // Add monthly investment and apply return
        double newValue = (currentValue + monthlyInvestment) * (1 + monthlyReturn);
        
        // Recurse for remaining months
        return calculatePortfolioGrowth(newValue, monthlyInvestment, monthlyReturn, monthsRemaining - 1);
    }
    
    /**
     * RECURSIVE LOAN CALCULATION
     * Calculates remaining loan balance after payments
     * Time Complexity: O(n) - Linear recursion
     * Space Complexity: O(n) - Call stack
     */
    public double calculateLoanBalance(double currentBalance, double monthlyPayment, 
                                     double monthlyInterestRate, int paymentsRemaining) {
        recursiveCallCount++;
        
        // Base case: no more payments or balance is zero
        if (paymentsRemaining == 0 || currentBalance <= 0) {
            return Math.max(0, currentBalance);
        }
        
        // Apply interest and subtract payment
        double newBalance = currentBalance * (1 + monthlyInterestRate) - monthlyPayment;
        
        // Recurse for remaining payments
        return calculateLoanBalance(newBalance, monthlyPayment, monthlyInterestRate, paymentsRemaining - 1);
    }
    
    /**
     * RECURSIVE PRESENT VALUE CALCULATION
     * Calculates present value of future cash flows
     * Time Complexity: O(n) - Linear recursion
     * Space Complexity: O(n) - Call stack
     */
    public double calculatePresentValue(double[] futureCashFlows, double discountRate, int index) {
        recursiveCallCount++;
        
        // Base case: processed all cash flows
        if (index >= futureCashFlows.length) {
            return 0;
        }
        
        // Calculate present value of current cash flow
        double presentValueCurrent = futureCashFlows[index] / Math.pow(1 + discountRate, index + 1);
        
        // Add present value of remaining cash flows
        return presentValueCurrent + calculatePresentValue(futureCashFlows, discountRate, index + 1);
    }
    
    /**
     * TAIL RECURSIVE OPTIMIZATION EXAMPLE
     * Optimized version using tail recursion
     * Time Complexity: O(n) - Linear
     * Space Complexity: O(1) - Tail call optimization
     */
    public double calculateCompoundInterestTailRecursive(double principal, double rate, 
                                                       int years, double accumulator) {
        recursiveCallCount++;
        
        // Base case
        if (years == 0) {
            return accumulator;
        }
        
        // Tail recursive call
        return calculateCompoundInterestTailRecursive(principal, rate, years - 1, 
                                                    accumulator * (1 + rate));
    }
    
    // Helper method for tail recursive compound interest
    public double calculateCompoundInterestTailRecursive(double principal, double rate, int years) {
        return calculateCompoundInterestTailRecursive(principal, rate, years, principal);
    }
    
    // Utility methods
    public void resetCounters() {
        recursiveCallCount = 0;
        memoizedCallCount = 0;
        memoizationCache.clear();
        fibCache.clear();
    }
    
    public int getRecursiveCallCount() { return recursiveCallCount; }
    public int getMemoizedCallCount() { return memoizedCallCount; }
    
    /**
     * Generate detailed financial forecast report
     */
    public void generateForecastReport(double principal, double rate, int years) {
        System.out.println("\n=== FINANCIAL FORECAST REPORT ===");
        System.out.println("Principal Amount: $" + df.format(principal));
        System.out.println("Annual Interest Rate: " + (rate * 100) + "%");
        System.out.println("Investment Period: " + years + " years");
        System.out.println("================================");
        
        // Year-by-year breakdown
        System.out.println("\nYear-by-Year Growth:");
        System.out.println("-------------------");
        double currentValue = principal;
        for (int year = 1; year <= years; year++) {
            currentValue *= (1 + rate);
            System.out.printf("Year %2d: $%s\n", year, df.format(currentValue));
        }
        
        System.out.println("\nFinal Value: $" + df.format(currentValue));
        System.out.println("Total Growth: $" + df.format(currentValue - principal));
        System.out.println("Growth Percentage: " + 
                          df.format(((currentValue - principal) / principal) * 100) + "%");
    }
}

// Demo class to showcase the Financial Forecasting System
public class FinancialForecastingDemo {
    public static void main(String[] args) {
        FinancialForecastingSystem ffs = new FinancialForecastingSystem();
        DecimalFormat df = new DecimalFormat("#,##0.00");
        
        System.out.println("=== FINANCIAL FORECASTING SYSTEM ===");
        System.out.println("Demonstrating Recursive Financial Algorithms\n");
        
        // 1. Compound Interest Calculation
        System.out.println("1. COMPOUND INTEREST CALCULATION");
        System.out.println("================================");
        double principal = 10000;
        double rate = 0.08; // 8% annual rate
        int years = 10;
        
        ffs.resetCounters();
        double futureValue = ffs.calculateCompoundInterestRecursive(principal, rate, years);
        System.out.println("Basic Recursive Approach:");
        System.out.println("Principal: $" + df.format(principal));
        System.out.println("Rate: " + (rate * 100) + "% annually");
        System.out.println("Years: " + years);
        System.out.println("Future Value: $" + df.format(futureValue));
        System.out.println("Recursive Calls: " + ffs.getRecursiveCallCount());
        
        // Memoized version
        ffs.resetCounters();
        double memoizedValue = ffs.calculateCompoundInterestMemoized(principal, rate, years);
        System.out.println("\nMemoized Recursive Approach:");
        System.out.println("Future Value: $" + df.format(memoizedValue));
        System.out.println("Memoized Calls: " + ffs.getMemoizedCallCount());
        
        // Tail recursive version
        ffs.resetCounters();
        double tailRecursiveValue = ffs.calculateCompoundInterestTailRecursive(principal, rate, years);
        System.out.println("\nTail Recursive Approach:");
        System.out.println("Future Value: $" + df.format(tailRecursiveValue));
        System.out.println("Recursive Calls: " + ffs.getRecursiveCallCount());
        
        // 2. Fibonacci Growth Prediction
        System.out.println("\n\n2. FIBONACCI GROWTH PREDICTION");
        System.out.println("===============================");
        double baseValue = 1000;
        int periods = 8;
        
        ffs.resetCounters();
        double fibGrowth = ffs.fibonacciGrowthPrediction(baseValue, periods);
        System.out.println("Basic Fibonacci Growth (Period " + periods + "):");
        System.out.println("Base Value: $" + df.format(baseValue));
        System.out.println("Predicted Value: $" + df.format(fibGrowth));
        System.out.println("Recursive Calls: " + ffs.getRecursiveCallCount());
        
        ffs.resetCounters();
        double fibMemoized = ffs.fibonacciGrowthMemoized(baseValue, periods);
        System.out.println("\nMemoized Fibonacci Growth:");
        System.out.println("Predicted Value: $" + df.format(fibMemoized));
        System.out.println("Memoized Calls: " + ffs.getMemoizedCallCount());
        
        // 3. Trend Analysis
        System.out.println("\n\n3. TREND ANALYSIS PREDICTION");
        System.out.println("=============================");
        double[] historicalData = {1000, 1100, 1250, 1400, 1600, 1850};
        int periodsAhead = 3;
        double trendFactor = 0.8;
        
        ffs.resetCounters();
        double trendPrediction = ffs.predictFutureTrend(historicalData, 
                                                       historicalData.length - 1, 
                                                       trendFactor, periodsAhead);
        System.out.println("Historical Data: " + Arrays.toString(historicalData));
        System.out.println("Periods Ahead: " + periodsAhead);
        System.out.println("Trend Factor: " + trendFactor);
        System.out.println("Predicted Value: $" + df.format(trendPrediction));
        System.out.println("Recursive Calls: " + ffs.getRecursiveCallCount());
        
        // 4. Portfolio Growth
        System.out.println("\n\n4. PORTFOLIO GROWTH CALCULATION");
        System.out.println("================================");
        double currentValue = 5000;
        double monthlyInvestment = 500;
        double monthlyReturn = 0.007; // 0.7% monthly
        int months = 24;
        
        ffs.resetCounters();
        double portfolioValue = ffs.calculatePortfolioGrowth(currentValue, monthlyInvestment, 
                                                           monthlyReturn, months);
        System.out.println("Initial Portfolio Value: $" + df.format(currentValue));
        System.out.println("Monthly Investment: $" + df.format(monthlyInvestment));
        System.out.println("Monthly Return: " + (monthlyReturn * 100) + "%");
        System.out.println("Investment Period: " + months + " months");
        System.out.println("Final Portfolio Value: $" + df.format(portfolioValue));
        System.out.println("Total Growth: $" + df.format(portfolioValue - currentValue - (monthlyInvestment * months)));
        System.out.println("Recursive Calls: " + ffs.getRecursiveCallCount());
        
        // 5. Loan Balance Calculation
        System.out.println("\n\n5. LOAN BALANCE CALCULATION");
        System.out.println("============================");
        double loanBalance = 50000;
        double monthlyPayment = 800;
        double monthlyInterestRate = 0.004; // 0.4% monthly (4.8% annually)
        int payments = 60;
        
        ffs.resetCounters();
        double remainingBalance = ffs.calculateLoanBalance(loanBalance, monthlyPayment, 
                                                         monthlyInterestRate, payments);
        System.out.println("Initial Loan Balance: $" + df.format(loanBalance));
        System.out.println("Monthly Payment: $" + df.format(monthlyPayment));
        System.out.println("Monthly Interest Rate: " + (monthlyInterestRate * 100) + "%");
        System.out.println("Number of Payments: " + payments);
        System.out.println("Remaining Balance: $" + df.format(remainingBalance));
        System.out.println("Recursive Calls: " + ffs.getRecursiveCallCount());
        
        // 6. Present Value Calculation
        System.out.println("\n\n6. PRESENT VALUE CALCULATION");
        System.out.println("=============================");
        double[] futureCashFlows = {1000, 1500, 2000, 2500, 3000};
        double discountRate = 0.10; // 10% discount rate
        
        ffs.resetCounters();
        double presentValue = ffs.calculatePresentValue(futureCashFlows, discountRate, 0);
        System.out.println("Future Cash Flows: " + Arrays.toString(futureCashFlows));
        System.out.println("Discount Rate: " + (discountRate * 100) + "%");
        System.out.println("Present Value: $" + df.format(presentValue));
        System.out.println("Recursive Calls: " + ffs.getRecursiveCallCount());
        
        // 7. Performance Comparison
        System.out.println("\n\n7. PERFORMANCE COMPARISON");
        System.out.println("==========================");
        System.out.println("Fibonacci Growth Comparison (Period 10):");
        
        // Regular Fibonacci
        long startTime = System.nanoTime();
        ffs.resetCounters();
        ffs.fibonacciGrowthPrediction(1000, 10);
        long regularTime = System.nanoTime() - startTime;
        int regularCalls = ffs.getRecursiveCallCount();
        
        // Memoized Fibonacci
        startTime = System.nanoTime();
        ffs.resetCounters();
        ffs.fibonacciGrowthMemoized(1000, 10);
        long memoizedTime = System.nanoTime() - startTime;
        int memoizedCalls = ffs.getMemoizedCallCount();
        
        System.out.println("Regular Fibonacci:");
        System.out.println("  Time: " + regularTime / 1000 + " microseconds");
        System.out.println("  Calls: " + regularCalls);
        System.out.println("Memoized Fibonacci:");
        System.out.println("  Time: " + memoizedTime / 1000 + " microseconds");
        System.out.println("  Calls: " + memoizedCalls);
        System.out.println("Improvement: " + (regularTime / (double) memoizedTime) + "x faster");
        
        // 8. Generate detailed report
        ffs.generateForecastReport(10000, 0.08, 10);
        
        // 9. Algorithm Analysis
        System.out.println("\n\n=== ALGORITHM ANALYSIS ===");
        System.out.println("Time Complexity Analysis:");
        System.out.println("• Compound Interest (Basic): O(n) - Linear recursion");
        System.out.println("• Compound Interest (Memoized): O(n) - Each subproblem solved once");
        System.out.println("• Fibonacci Growth (Basic): O(2^n) - Exponential due to overlapping subproblems");
        System.out.println("• Fibonacci Growth (Memoized): O(n) - Dynamic programming optimization");
        System.out.println("• Trend Analysis: O(n) - Linear recursion");
        System.out.println("• Portfolio Growth: O(n) - Linear recursion");
        System.out.println("• Present Value: O(n) - Linear recursion");
        
        System.out.println("\nSpace Complexity Analysis:");
        System.out.println("• All recursive algorithms: O(n) - Due to call stack");
        System.out.println("• Memoized versions: O(n) - Additional cache storage");
        System.out.println("• Tail recursive: O(1) - Optimized by compiler");
        
        System.out.println("\nOptimization Techniques:");
        System.out.println("• Memoization: Cache results to avoid recalculation");
        System.out.println("• Tail Recursion: Enable compiler optimization");
        System.out.println("• Iterative Conversion: Replace recursion with loops");
        System.out.println("• Dynamic Programming: Bottom-up approach");
        
        System.out.println("\nWhen to Use Recursive Approaches:");
        System.out.println("✓ Problem has optimal substructure");
        System.out.println("✓ Natural recursive definition exists");
        System.out.println("✓ Overlapping subproblems (use memoization)");
        System.out.println("✓ Tree-like problem structure");
        System.out.println("✗ Simple iterative solution exists");
        System.out.println("✗ Deep recursion with stack overflow risk");
        System.out.println("✗ No overlapping subproblems");
    }
}