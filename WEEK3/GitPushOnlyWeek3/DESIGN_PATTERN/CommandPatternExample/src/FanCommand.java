/**
 * Concrete Command - Control fan speed
 */
public class FanCommand implements Command {
    private Fan fan;
    private int newSpeed;
    private int previousSpeed;
    
    public FanCommand(Fan fan, int speed) {
        this.fan = fan;
        this.newSpeed = speed;
    }
    
    @Override
    public void execute() {
        previousSpeed = fan.getSpeed();
        fan.setSpeed(newSpeed);
    }
    
    @Override
    public void undo() {
        fan.setSpeed(previousSpeed);
    }
    
    @Override
    public String getCommandName() {
        String[] speedNames = {"OFF", "LOW", "MEDIUM", "HIGH"};
        return "Set " + fan.getLocation() + " Fan to " + speedNames[newSpeed];
    }
}
