/**
 * Null Object Pattern - No operation command
 */
public class NoCommand implements Command {
    @Override
    public void execute() {
        // Do nothing
    }
    
    @Override
    public void undo() {
        // Do nothing
    }
    
    @Override
    public String getCommandName() {
        return "No Command";
    }
}