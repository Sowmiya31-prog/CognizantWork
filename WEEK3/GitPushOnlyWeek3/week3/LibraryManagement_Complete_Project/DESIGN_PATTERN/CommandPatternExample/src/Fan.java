/**
 * Additional Receiver Class - Fan device
 */
public class Fan {
    private String location;
    private int speed; // 0 = off, 1 = low, 2 = medium, 3 = high
    private String[] speedNames = {"OFF", "LOW", "MEDIUM", "HIGH"};
    
    public Fan(String location) {
        this.location = location;
        this.speed = 0;
    }
    
    public void turnOff() {
        speed = 0;
        System.out.println("🌪️ " + location + " fan is OFF");
    }
    
    public void setSpeed(int speed) {
        if (speed >= 0 && speed <= 3) {
            this.speed = speed;
            System.out.println("💨 " + location + " fan speed: " + speedNames[speed]);
        }
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public String getLocation() {
        return location;
    }
    
    public String getStatus() {
        return location + " fan: " + speedNames[speed];
    }
}