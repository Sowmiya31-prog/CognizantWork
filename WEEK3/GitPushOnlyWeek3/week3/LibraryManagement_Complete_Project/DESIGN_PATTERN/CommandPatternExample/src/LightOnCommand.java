/**
 * Concrete Command - Turn light on
 */
public class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOn();
    }
    
    @Override
    public void undo() {
        light.turnOff();
    }
    
    @Override
    public String getCommandName() {
        return "Turn " + light.getLocation() + " Light ON";
    }
}
