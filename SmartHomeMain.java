public class SmartHomeMain {
    public static void main(String[] args) {
        LightBulb bulb1 = new LightBulb("Living Room Bulb", "Living Room");
        Thermostat thermo1 = new Thermostat("Kitchen Thermostat", "ON", "Hallway", 24.5, "Cooling");
        Thermostat thermo2 = new Thermostat("Bedroom Thermostat", "OFF", "Bedroom", 34.0, "Heating");

        Device[] devices = { bulb1, thermo1, thermo2 };

        System.out.println("\n_______________________________________");
        System.out.println("       Initial Device Status         ");
        System.out.println("_______________________________________");
        for (Device d : devices) d.displayInfo();
        Device.showDeviceCount();

        SmartHomeController controller = new SmartHomeController(devices);

        controller.turnAllOff();
        System.out.println("\n_______________________________________");
        System.out.println("    Device Status After Turning OFF    ");
        System.out.println("_______________________________________");
        for (Device d : devices) d.displayInfo();

        controller.turnAllOn();
        System.out.println("\n_______________________________________");
        System.out.println("    Device Status After Turning ON     ");
        System.out.println("_______________________________________");
        for (Device d : devices) d.displayInfo();
    }
}

class Device {
    String deviceName;
    String status;
    String location;
    static int deviceCount = 0;

    public Device() {
        this.deviceName = "Unknown Device";
        this.status = "OFF";
        this.location = "Unknown";
        deviceCount++;
    }

    public Device(String deviceName, String status, String location) {
        this.deviceName = deviceName;
      
        if (status.equalsIgnoreCase("ON")) {
            this.status = status.toUpperCase();
        } else {
            this.status = "OFF";
        }
        this.location = location;
        deviceCount++;
    }

    public void turnOn() { status = "ON"; }
    public void turnOff() { status = "OFF"; }

    public void displayInfo() {
        System.out.println("\nDevice Name: " + deviceName);
        System.out.println("Status: " + status);
        System.out.println("Location: " + location);
    }

    public static void showDeviceCount() {
        System.out.println("\nTotal devices created: " + deviceCount);
    }
}

class LightBulb extends Device {
    private int brightnessLevel;
    private String color;

    public LightBulb(String deviceName, String status, String location, int brightnessLevel, String color) {
        super(deviceName, status, location);
        this.brightnessLevel = brightnessLevel;
        this.color = color;
    }

    
    public LightBulb(String deviceName, String location) {
        super(deviceName, "OFF", location);
        this.brightnessLevel = 80;  
        this.color = "Bright";    
    }

    public void adjustBrightness(int level) {
        if (level >= 0 && level <= 100) {  
            brightnessLevel = level;
            if (level == 0) {
                color = "No Light";
            }
        } else {
            System.out.println("Invalid brightness level (0–100 only).");
        }
    }

    @Override
    public void turnOff() {
        super.turnOff();
        brightnessLevel = 0;
        color = "No Light";
    }

    @Override
    public void turnOn() {
        super.turnOn();
        brightnessLevel = 50;
        color = "Warm White";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Brightness Level: " + brightnessLevel + "%");
        System.out.println("Color: " + color);
    }
}

class Thermostat extends Device {
    private double temperature;
    private String mode;

    public Thermostat(String deviceName, String status, String location, double temperature, String mode) {
        super(deviceName, status, location);
        this.temperature = temperature;
        this.mode = mode;
    }

    public void setTemperature(double temp) {
        this.temperature = temp;
    }

    public void setTemperature(double temp, String mode) {
        this.temperature = temp;
        this.mode = mode;
    }

    @Override
    public void turnOff() {
        super.turnOff();
        temperature = 0;
    }

    @Override
    public void turnOn() {
        super.turnOn();
        if (temperature == 0) temperature = 22.0; 
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        if (status.equals("OFF")) {
            System.out.println("Temperature: -- (Device Off)");
        } else {
            System.out.println("Temperature: " + temperature + "°C");
        }
        System.out.println("Mode: " + mode);
    }
}

class SmartHomeController {
    private Device[] devices;

    public SmartHomeController(Device[] devices) {
        this.devices = devices;
    }

    public void turnAllOn() {
        for (Device d : devices) d.turnOn();
        System.out.println("\nAll devices turned ON.");
    }

    public void turnAllOff() {
        for (Device d : devices) d.turnOff();
        System.out.println("\nAll devices turned OFF.");
    }
}
