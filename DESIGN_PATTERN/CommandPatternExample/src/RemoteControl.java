import java.util.ArrayList;
import java.util.List;

/**
 * Invoker Class - Remote control that executes commands
 */
public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private List<Command> commandHistory;
    private Command lastCommand;
    private int numSlots;
    
    public RemoteControl(int numSlots) {
        this.numSlots = numSlots;
        onCommands = new Command[numSlots];
        offCommands = new Command[numSlots];
        commandHistory = new ArrayList<>();
        
        // Initialize all slots with NoCommand
        Command noCommand = new NoCommand();
        for (int i = 0; i < numSlots; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        lastCommand = noCommand;
    }
    
    /**
     * Set command for a specific slot
     */
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        if (slot >= 0 && slot < numSlots) {
            onCommands[slot] = onCommand;
            offCommands[slot] = offCommand;
            System.out.println("📡 Slot " + slot + " configured:");
            System.out.println("  ON:  " + onCommand.getCommandName());
            System.out.println("  OFF: " + offCommand.getCommandName());
        }
    }
    
    /**
     * Press ON button for specific slot
     */
    public void onButtonPressed(int slot) {
        if (slot >= 0 && slot < numSlots) {
            System.out.println("\n🔘 Remote: ON button pressed for slot " + slot);
            onCommands[slot].execute();
            lastCommand = onCommands[slot];
            commandHistory.add(lastCommand);
        }
    }
    
    /**
     * Press OFF button for specific slot
     */
    public void offButtonPressed(int slot) {
        if (slot >= 0 && slot < numSlots) {
            System.out.println("\n🔘 Remote: OFF button pressed for slot " + slot);
            offCommands[slot].execute();
            lastCommand = offCommands[slot];
            commandHistory.add(lastCommand);
        }
    }
    
    /**
     * Press UNDO button
     */
    public void undoButtonPressed() {
        System.out.println("\n↩️ Remote: UNDO button pressed");
        lastCommand.undo();
    }
    
    /**
     * Execute any command directly
     */
    public void executeCommand(Command command) {
        System.out.println("\n🎮 Remote: Executing command directly");
        command.execute();
        lastCommand = command;
        commandHistory.add(command);
    }
    
    /**
     * Display remote control status
     */
    public void displayStatus() {
        System.out.println("\n📻 Remote Control Status:");
        System.out.println("-".repeat(40));
        for (int i = 0; i < numSlots; i++) {
            System.out.println("Slot " + i + " - ON: " + onCommands[i].getCommandName());
            System.out.println("       - OFF: " + offCommands[i].getCommandName());
        }
        System.out.println("Last Command: " + lastCommand.getCommandName());
    }
    
    /**
     * Display command history
     */
    public void displayHistory() {
        System.out.println("\n📜 Command History:");
        System.out.println("-".repeat(30));
        if (commandHistory.isEmpty()) {
            System.out.println("No commands executed yet.");
        } else {
            for (int i = 0; i < commandHistory.size(); i++) {
                System.out.println((i + 1) + ". " + commandHistory.get(i).getCommandName());
            }
        }
    }
    
    /**
     * Clear command history
     */
    public void clearHistory() {
        commandHistory.clear();
        System.out.println("📜 Command history cleared.");
    }
}