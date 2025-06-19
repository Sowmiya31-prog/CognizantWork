// SingletonTest.java - Test class to verify Singleton implementation
public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Singleton Pattern Implementation ===\n");

        // Test 1: Get first instance
        System.out.println("Getting first Logger instance...");
        Logger logger1 = Logger.getInstance();
        logger1.showInstanceInfo();

        // Test 2: Get second instance
        System.out.println("\nGetting second Logger instance...");
        Logger logger2 = Logger.getInstance();
        logger2.showInstanceInfo();

        // Test 3: Verify both references point to same instance
        System.out.println("\n=== Verification Tests ===");
        System.out.println("Are both instances the same object? " + (logger1 == logger2));
        System.out.println("Hash codes match? " + (logger1.hashCode() == logger2.hashCode()));

        // Test 4: Test logging functionality
        System.out.println("\n=== Testing Logging Functionality ===");
        logger1.logInfo("Application started successfully");
        logger2.logWarning("This is a warning message");
        logger1.logError("An error occurred during processing");

        // Test 5: Multi-threaded test to ensure thread safety
        System.out.println("\n=== Testing Thread Safety ===");
        testThreadSafety();
    }

    // Method to test thread safety of Singleton implementation
    private static void testThreadSafety() {
        final Logger[] loggers = new Logger[10];

        // Create multiple threads that try to get Logger instance simultaneously
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                loggers[index] = Logger.getInstance();
                System.out.println("Thread " + index + " got Logger with hash: " +
                        loggers[index].hashCode());
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }

        // Verify all threads got the same instance
        boolean allSame = true;
        for (int i = 1; i < loggers.length; i++) {
            if (loggers[i] != loggers[0]) {
                allSame = false;
                break;
            }
        }

        System.out.println("All threads got same instance? " + allSame);
    }
}

