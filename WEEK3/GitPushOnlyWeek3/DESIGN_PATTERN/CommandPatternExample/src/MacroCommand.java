import java.util.ArrayList;
import java.util.List;

/**
 * Macro Command - Execute multiple commands together
 */
public class MacroCommand implements Command {
    private List<Command> commands;
    private String macroName;
    
    public MacroCommand(String macroName, Command... commands) {
        this.macroName = macroName;
        this.commands = new ArrayList<>();
        for (Command command : commands) {
            this.commands.add(command);
        }
    }
    
    @Override
    public void execute() {
        System.out.println("🎯 Executing macro: " + macroName);
        for (Command command : commands) {
            command.execute();
        }
    }
    
    @Override
    public void undo() {
        System.out.println("↩️ Undoing macro: " + macroName);
        // Undo in reverse order
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
    
    @Override
    public String getCommandName() {
        return "Macro: " + macroName;
    }
}
