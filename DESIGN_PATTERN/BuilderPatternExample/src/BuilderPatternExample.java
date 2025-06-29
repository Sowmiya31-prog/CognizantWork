import java.util.ArrayList;
import java.util.List;

// 1. Product Class - Computer with multiple optional components
class Computer {
    // Required parameters
    private final String cpu;
    private final String motherboard;
    
    // Optional parameters
    private final String ram;
    private final String storage;
    private final String graphicsCard;
    private final String powerSupply;
    private final String coolingSystem;
    private final String caseType;
    private final List<String> peripherals;
    private final boolean isGamingPC;
    private final boolean hasWiFi;
    private final boolean hasBluetooth;
    private final String operatingSystem;
    private final int warrantyYears;
    
    // Private constructor - only accessible through Builder
    private Computer(Builder builder) {
        // Required parameters
        this.cpu = builder.cpu;
        this.motherboard = builder.motherboard;
        
        // Optional parameters
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.powerSupply = builder.powerSupply;
        this.coolingSystem = builder.coolingSystem;
        this.caseType = builder.caseType;
        this.peripherals = new ArrayList<>(builder.peripherals);
        this.isGamingPC = builder.isGamingPC;
        this.hasWiFi = builder.hasWiFi;
        this.hasBluetooth = builder.hasBluetooth;
        this.operatingSystem = builder.operatingSystem;
        this.warrantyYears = builder.warrantyYears;
    }
    
    // Getter methods
    public String getCpu() { return cpu; }
    public String getMotherboard() { return motherboard; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGraphicsCard() { return graphicsCard; }
    public String getPowerSupply() { return powerSupply; }
    public String getCoolingSystem() { return coolingSystem; }
    public String getCaseType() { return caseType; }
    public List<String> getPeripherals() { return new ArrayList<>(peripherals); }
    public boolean isGamingPC() { return isGamingPC; }
    public boolean hasWiFi() { return hasWiFi; }
    public boolean hasBluetooth() { return hasBluetooth; }
    public String getOperatingSystem() { return operatingSystem; }
    public int getWarrantyYears() { return warrantyYears; }
    
    // 2. Static nested Builder class
    public static class Builder {
        // Required parameters
        private String cpu;
        private String motherboard;
        
        // Optional parameters with default values
        private String ram = "8GB DDR4";
        private String storage = "256GB SSD";
        private String graphicsCard = "Integrated";
        private String powerSupply = "500W";
        private String coolingSystem = "Stock Cooler";
        private String caseType = "Mid Tower";
        private List<String> peripherals = new ArrayList<>();
        private boolean isGamingPC = false;
        private boolean hasWiFi = true;
        private boolean hasBluetooth = false;
        private String operatingSystem = "Windows 11";
        private int warrantyYears = 1;
        
        // Constructor with required parameters
        public Builder(String cpu, String motherboard) {
            this.cpu = cpu;
            this.motherboard = motherboard;
        }
        
        // Builder methods for optional parameters
        public Builder ram(String ram) {
            this.ram = ram;
            return this;
        }
        
        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public Builder graphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }
        
        public Builder powerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }
        
        public Builder coolingSystem(String coolingSystem) {
            this.coolingSystem = coolingSystem;
            return this;
        }
        
        public Builder caseType(String caseType) {
            this.caseType = caseType;
            return this;
        }
        
        public Builder addPeripheral(String peripheral) {
            this.peripherals.add(peripheral);
            return this;
        }
        
        public Builder peripherals(List<String> peripherals) {
            this.peripherals = new ArrayList<>(peripherals);
            return this;
        }
        
        public Builder isGamingPC(boolean isGamingPC) {
            this.isGamingPC = isGamingPC;
            return this;
        }
        
        public Builder hasWiFi(boolean hasWiFi) {
            this.hasWiFi = hasWiFi;
            return this;
        }
        
        public Builder hasBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }
        
        public Builder operatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }
        
        public Builder warrantyYears(int warrantyYears) {
            this.warrantyYears = warrantyYears;
            return this;
        }
        
        // Build method that creates and returns Computer instance
        public Computer build() {
            // Optional: Add validation logic here
            validateComputer();
            return new Computer(this);
        }
        
        // Private validation method
        private void validateComputer() {
            if (cpu == null || cpu.trim().isEmpty()) {
                throw new IllegalStateException("CPU is required");
            }
            if (motherboard == null || motherboard.trim().isEmpty()) {
                throw new IllegalStateException("Motherboard is required");
            }
            if (warrantyYears < 0 || warrantyYears > 10) {
                throw new IllegalStateException("Warranty years must be between 0 and 10");
            }
        }
    }
    
    // toString method for easy display
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Computer Configuration:\n");
        sb.append("├── CPU: ").append(cpu).append("\n");
        sb.append("├── Motherboard: ").append(motherboard).append("\n");
        sb.append("├── RAM: ").append(ram).append("\n");
        sb.append("├── Storage: ").append(storage).append("\n");
        sb.append("├── Graphics Card: ").append(graphicsCard).append("\n");
        sb.append("├── Power Supply: ").append(powerSupply).append("\n");
        sb.append("├── Cooling System: ").append(coolingSystem).append("\n");
        sb.append("├── Case Type: ").append(caseType).append("\n");
        sb.append("├── Operating System: ").append(operatingSystem).append("\n");
        sb.append("├── Gaming PC: ").append(isGamingPC ? "Yes" : "No").append("\n");
        sb.append("├── WiFi: ").append(hasWiFi ? "Yes" : "No").append("\n");
        sb.append("├── Bluetooth: ").append(hasBluetooth ? "Yes" : "No").append("\n");
        sb.append("├── Warranty: ").append(warrantyYears).append(" year(s)\n");
        sb.append("└── Peripherals: ");
        if (peripherals.isEmpty()) {
            sb.append("None");
        } else {
            sb.append(String.join(", ", peripherals));
        }
        return sb.toString();
    }
}

// 3. Pre-configured Computer Builder Classes (Optional Extension)
class GamingComputerBuilder {
    public static Computer.Builder getGamingBuilder() {
        return new Computer.Builder("Intel Core i7-13700K", "ASUS ROG Strix Z790")
                .ram("32GB DDR5")
                .storage("1TB NVMe SSD")
                .graphicsCard("NVIDIA RTX 4070")
                .powerSupply("750W 80+ Gold")
                .coolingSystem("Liquid Cooling AIO")
                .caseType("Full Tower RGB")
                .isGamingPC(true)
                .hasWiFi(true)
                .hasBluetooth(true)
                .warrantyYears(3);
    }
}

class OfficeComputerBuilder {
    public static Computer.Builder getOfficeBuilder() {
        return new Computer.Builder("Intel Core i5-13400", "MSI Pro B760M")
                .ram("16GB DDR4")
                .storage("512GB SSD")
                .powerSupply("450W 80+ Bronze")
                .caseType("Mini ITX")
                .hasWiFi(true)
                .warrantyYears(2);
    }
}

class BudgetComputerBuilder {
    public static Computer.Builder getBudgetBuilder() {
        return new Computer.Builder("AMD Ryzen 5 5600G", "ASRock B450M")
                .ram("8GB DDR4")
                .storage("256GB SSD")
                .powerSupply("400W")
                .caseType("Micro ATX")
                .warrantyYears(1);
    }
}

// 4. Test Class - Main Application
public class BuilderPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Demo - Computer Configuration ===\n");
        
        // Example 1: Basic computer with minimal configuration
        System.out.println("1. Basic Computer Configuration:");
        System.out.println("-".repeat(60));
        Computer basicComputer = new Computer.Builder("Intel Core i3-12100", "MSI H610M")
                .build();
        System.out.println(basicComputer);
        System.out.println();
        
        // Example 2: Gaming computer with extensive configuration
        System.out.println("2. High-End Gaming Computer:");
        System.out.println("-".repeat(60));
        Computer gamingComputer = new Computer.Builder("AMD Ryzen 9 7900X", "ASUS ROG Crosshair X670E")
                .ram("64GB DDR5-6000")
                .storage("2TB NVMe SSD + 4TB HDD")
                .graphicsCard("NVIDIA RTX 4090")
                .powerSupply("1000W 80+ Platinum")
                .coolingSystem("Custom Water Cooling Loop")
                .caseType("Full Tower Tempered Glass")
                .addPeripheral("Mechanical Gaming Keyboard")
                .addPeripheral("Gaming Mouse")
                .addPeripheral("4K Gaming Monitor")
                .addPeripheral("Gaming Headset")
                .isGamingPC(true)
                .hasWiFi(true)
                .hasBluetooth(true)
                .operatingSystem("Windows 11 Pro")
                .warrantyYears(5)
                .build();
        System.out.println(gamingComputer);
        System.out.println();
        
        // Example 3: Office computer using method chaining
        System.out.println("3. Office Workstation:");
        System.out.println("-".repeat(60));
        Computer officeComputer = new Computer.Builder("Intel Core i7-13700", "Dell OptiPlex Motherboard")
                .ram("32GB DDR4")
                .storage("1TB SSD")
                .graphicsCard("Intel UHD Graphics")
                .powerSupply("500W")
                .coolingSystem("Stock Intel Cooler")
                .caseType("Small Form Factor")
                .addPeripheral("Wireless Keyboard")
                .addPeripheral("Wireless Mouse")
                .addPeripheral("24-inch Monitor")
                .hasWiFi(true)
                .hasBluetooth(true)
                .operatingSystem("Windows 11 Enterprise")
                .warrantyYears(3)
                .build();
        System.out.println(officeComputer);
        System.out.println();
        
        // Example 4: Using pre-configured builders
        System.out.println("4. Pre-configured Computer Types:");
        System.out.println("-".repeat(60));
        
        // Gaming PC with additional customization
        Computer prebuiltGaming = GamingComputerBuilder.getGamingBuilder()
                .addPeripheral("RGB Mechanical Keyboard")
                .addPeripheral("Gaming Mouse Pad")
                .build();
        System.out.println("Gaming PC Configuration:");
        System.out.println(prebuiltGaming);
        System.out.println();
        
        // Office PC
        Computer officePC = OfficeComputerBuilder.getOfficeBuilder()
                .addPeripheral("Wireless Keyboard & Mouse Set")
                .build();
        System.out.println("Office PC Configuration:");
        System.out.println(officePC);
        System.out.println();
        
        // Budget PC
        Computer budgetPC = BudgetComputerBuilder.getBudgetBuilder()
                .build();
        System.out.println("Budget PC Configuration:");
        System.out.println(budgetPC);
        System.out.println();
        
        // Example 5: Demonstrating validation
        System.out.println("5. Builder Validation Demo:");
        System.out.println("-".repeat(60));
        try {
            // This will throw an exception due to invalid warranty years
            Computer invalidComputer = new Computer.Builder("Intel i5", "Generic Motherboard")
                    .warrantyYears(15) // Invalid warranty period
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
        
        try {
            // This will throw an exception due to empty CPU
            Computer invalidComputer2 = new Computer.Builder("", "Generic Motherboard")
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("Builder Pattern Benefits Demonstrated:");
        System.out.println("• Flexible object construction with optional parameters");
        System.out.println("• Method chaining for readable code");
        System.out.println("• Immutable objects (thread-safe)");
        System.out.println("• Built-in validation");
        System.out.println("• Pre-configured builders for common configurations");
    }
}

