public class SmartHomeMain {
    public static void main(String[] args) {

        LightBulb bulb1 = new LightBulb("Living Room Bulb", "OFF", "Living Room", 50, "Dim");
        Thermostat thermo1 = new Thermostat("Kitchen Thermostat", "ON", "Hallway", 24.5, "cooling");
        Thermostat thermo2 = new Thermostat("Bedroom Thermostat", "OFF", "Bedroom", 34.0, "heating");

        Device[] devices = {bulb1, thermo1, thermo2};


        System.out.println("=== Initial Device Status ===");
        for (Device d : devices) {
            d.displayInfo();
        }
        Device.showDeviceCount();

 
        SmartHomeController controller = new SmartHomeController(devices);

       
        controller.turnAllOff();
        System.out.println("\n=== Device Status After Turning OFF ===");
        for (Device d : devices) {
            d.displayInfo();
        }


        controller.turnAllOn();
        System.out.println("\n=== Device Status After Turning ON ===");
        for (Device d : devices) {
            d.displayInfo();
        }
    }
}


class Device {
    protected String deviceName;
    protected String status;
    protected String location;
    static int deviceCount = 0;


    public Device() {
        this("Unknown Device", "OFF", "Unknown");
    }

    public Device(String deviceName, String status, String location) {
        this.deviceName = deviceName;
        this.status = (status.equalsIgnoreCase("ON") || status.equalsIgnoreCase("OFF")) ? status.toUpperCase() : "OFF";
        this.location = location;
        deviceCount++;
    }


    public void turnOn() { status = "ON"; }
    public void turnOff() { status = "OFF"; }

    public void displayInfo() {
        System.out.println("Device Name: " + deviceName);
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
        this.brightnessLevel = 50;
        this.color = "Warm White";
    }


    public void adjustBrightness(int level) {
        if (level >= 0 && level <= 100)
            this.brightnessLevel = level;
        else
            System.out.println("Brightness must be between 0 and 100.");
    }


    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Brightness Level: " + brightnessLevel);
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

    // Method overloads
    public void setTemperature(double temp) {
        this.temperature = temp;
    }

    public void setTemperature(double temp, String mode) {
        this.temperature = temp;
        this.mode = mode;
    }

    // Override displayInfo
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Temperature: " + temperature + "°C");
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
