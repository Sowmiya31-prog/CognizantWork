/**
 * Test class to demonstrate the Command Pattern implementation
 */
public class HomeAutomationApp {
    public static void main(String[] args) {
        System.out.println("=== Command Pattern Example - Home Automation System ===\n");
        
        // Create receiver objects (devices)
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        Light bedroomLight = new Light("Bedroom");
        Fan livingRoomFan = new Fan("Living Room");
        Fan bedroomFan = new Fan("Bedroom");
        
        // Create command objects
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        
        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);
        
        LightDimCommand bedroomLightDim = new LightDimCommand(bedroomLight, 30);
        LightOffCommand bedroomLightOff = new LightOffCommand(bedroomLight);
        
        FanCommand livingRoomFanHigh = new FanCommand(livingRoomFan, 3);
        FanCommand livingRoomFanOff = new FanCommand(livingRoomFan, 0);
        
        FanCommand bedroomFanLow = new FanCommand(bedroomFan, 1);
        FanCommand bedroomFanOff = new FanCommand(bedroomFan, 0);
        
        // Create remote control (invoker)
        RemoteControl remote = new RemoteControl(5);
        
        // Configure remote control slots
        System.out.println("1. Configuring Remote Control:");
        System.out.println("=".repeat(40));
        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, kitchenLightOn, kitchenLightOff);
        remote.setCommand(2, bedroomLightDim, bedroomLightOff);
        remote.setCommand(3, livingRoomFanHigh, livingRoomFanOff);
        remote.setCommand(4, bedroomFanLow, bedroomFanOff);
        
        // Display initial status
        remote.displayStatus();
        
        // Test basic commands
        System.out.println("\n2. Testing Basic Commands:");
        System.out.println("=".repeat(40));
        remote.onButtonPressed(0);  // Living room light on
        remote.onButtonPressed(1);  // Kitchen light on
        remote.onButtonPressed(3);  // Living room fan high
        
        // Test undo functionality
        System.out.println("\n3. Testing Undo Functionality:");
        System.out.println("=".repeat(40));
        remote.undoButtonPressed(); // Undo living room fan
        remote.undoButtonPressed(); // Undo kitchen light
        
        // Test more commands
        System.out.println("\n4. Testing More Commands:");
        System.out.println("=".repeat(40));
        remote.onButtonPressed(2);  // Bedroom light dim
        remote.onButtonPressed(4);  // Bedroom fan low
        remote.offButtonPressed(0); // Living room light off
        
        // Create and test macro command
        System.out.println("\n5. Testing Macro Command:");
        System.out.println("=".repeat(40));
        MacroCommand eveningMacro = new MacroCommand("Evening Scene",
            new LightOnCommand(livingRoomLight),
            new LightDimCommand(kitchenLight, 50),
            new LightDimCommand(bedroomLight, 20),
            new FanCommand(livingRoomFan, 2)
        );
        
        remote.executeCommand(eveningMacro);
        
        // Test macro undo
        System.out.println("\n6. Testing Macro Undo:");
        System.out.println("=".repeat(40));
        remote.undoButtonPressed();
        
        // Create morning macro
        System.out.println("\n7. Testing Morning Macro:");
        System.out.println("=".repeat(40));
        MacroCommand morningMacro = new MacroCommand("Morning Scene",
            new LightOnCommand(kitchenLight),
            new LightOnCommand(livingRoomLight),
            new FanCommand(livingRoomFan, 1),
            new FanCommand(bedroomFan, 0)
        );
        
        remote.executeCommand(morningMacro);
        
        // Display command history
        remote.displayHistory();
        
        // Display final device status
        System.out.println("\n8. Final Device Status:");
        System.out.println("=".repeat(40));
        displayAllDeviceStatus(livingRoomLight, kitchenLight, bedroomLight, 
                            livingRoomFan, bedroomFan);
        
        // Demonstrate direct command execution
        System.out.println("\n9. Custom Brightness Control:");
        System.out.println("=".repeat(40));
        remote.executeCommand(new LightDimCommand(livingRoomLight, 75));
        remote.executeCommand(new LightDimCommand(kitchenLight, 90));
        
        // Final status
        remote.displayHistory();
        displayAllDeviceStatus(livingRoomLight, kitchenLight, bedroomLight, 
                            livingRoomFan, bedroomFan);
    }
    
    /**
     * Helper method to display status of all devices
     */
    private static void displayAllDeviceStatus(Light... lights) {
        System.out.println("🏠 Current Device Status:");
        for (Light light : lights) {
            System.out.println("  " + light.getStatus());
        }
    }
    
    private static void displayAllDeviceStatus(Light light1, Light light2, Light light3,
                                            Fan fan1, Fan fan2) {
        System.out.println("🏠 Current Device Status:");
        System.out.println("  " + light1.getStatus());
        System.out.println("  " + light2.getStatus());
        System.out.println("  " + light3.getStatus());
        System.out.println("  " + fan1.getStatus());
        System.out.println("  " + fan2.getStatus());
    }
}