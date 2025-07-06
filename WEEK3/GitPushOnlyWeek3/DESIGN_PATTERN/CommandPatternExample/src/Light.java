/**
 * Receiver Class - Light device that performs the actual work
 */
public class Light {
    private String location;
    private boolean isOn;
    private int brightness;
    
    public Light(String location) {
        this.location = location;
        this.isOn = false;
        this.brightness = 0;
    }
    
    public void turnOn() {
        isOn = true;
        brightness = 100;
        System.out.println("💡 " + location + " light is ON (Brightness: " + brightness + "%)");
    }
    
    public void turnOff() {
        isOn = false;
        brightness = 0;
        System.out.println("🌙 " + location + " light is OFF");
    }
    
    public void setBrightness(int level) {
        if (isOn && level >= 0 && level <= 100) {
            brightness = level;
            System.out.println("🔆 " + location + " light brightness set to " + brightness + "%");
        } else if (!isOn) {
            System.out.println("⚠️ Cannot set brightness - " + location + " light is OFF");
        }
    }
    
    public boolean isOn() {
        return isOn;
    }
    
    public int getBrightness() {
        return brightness;
    }
    
    public String getLocation() {
        return location;
    }
    
    public String getStatus() {
        return location + " light: " + (isOn ? "ON (" + brightness + "%)" : "OFF");
    }
}