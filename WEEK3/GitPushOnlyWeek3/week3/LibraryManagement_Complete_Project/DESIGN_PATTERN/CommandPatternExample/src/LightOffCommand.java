/**
 * Concrete Command - Turn light off
 */
public class LightOffCommand implements Command {
    private Light light;
    
    public LightOffCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOff();
    }
    
    @Override
    public void undo() {
        light.turnOn();
    }
    
    @Override
    public String getCommandName() {
        return "Turn " + light.getLocation() + " Light OFF";
    }
}