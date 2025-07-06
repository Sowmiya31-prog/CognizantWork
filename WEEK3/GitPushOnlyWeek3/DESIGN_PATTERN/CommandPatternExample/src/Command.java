/**
 * Command Interface - defines the contract for all commands
 */
public interface Command {
    void execute();
    void undo();
    String getCommandName();
}
