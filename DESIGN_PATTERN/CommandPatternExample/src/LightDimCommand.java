/**
 * Concrete Command - Dim light to specific brightness
 */
public class LightDimCommand implements Command {
    private Light light;
    private int newBrightness;
    private int previousBrightness;
    private boolean wasOn;
    
    public LightDimCommand(Light light, int brightness) {
        this.light = light;
        this.newBrightness = brightness;
    }
    
    @Override
    public void execute() {
        // Store previous state for undo
        previousBrightness = light.getBrightness();
        wasOn = light.isOn();
        
        if (!light.isOn()) {
            light.turnOn();
        }
        light.setBrightness(newBrightness);
    }
    
    @Override
    public void undo() {
        if (wasOn) {
            light.setBrightness(previousBrightness);
        } else {
            light.turnOff();
        }
    }
    
    @Override
    public String getCommandName() {
        return "Dim " + light.getLocation() + " Light to " + newBrightness + "%";
    }
}