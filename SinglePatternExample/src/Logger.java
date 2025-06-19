// Logger.java - Singleton Pattern Implementation
    public class Logger {
        // Step 1: Create a private static instance (lazy initialization)
        private static Logger instance;

        // Step 2: Make constructor private to prevent external instantiation
        private Logger() {
            System.out.println("Logger instance created!");
        }

        // Step 3: Provide public static method to get the singleton instance
        // Thread-safe implementation using double-checked locking
        public static Logger getInstance() {
            if (instance == null) {
                synchronized (Logger.class) {
                    if (instance == null) {
                        instance = new Logger();
                    }
                }
            }
            return instance;
        }

        // Logging methods
        public void logInfo(String message) {
            System.out.println("[INFO] " + getCurrentTimestamp() + ": " + message);
        }

        public void logError(String message) {
            System.out.println("[ERROR] " + getCurrentTimestamp() + ": " + message);
        }

        public void logWarning(String message) {
            System.out.println("[WARNING] " + getCurrentTimestamp() + ": " + message);
        }

        // Helper method to get current timestamp
        private String getCurrentTimestamp() {
            return java.time.LocalDateTime.now().toString();
        }

        // Method to demonstrate singleton behavior
        public void showInstanceInfo() {
            System.out.println("Logger instance hash code: " + this.hashCode());
        }
    }

