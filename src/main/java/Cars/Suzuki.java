package Cars;

public class Suzuki extends Car {
    private boolean allWheelDrive;

    public Suzuki(String model, String country, int year, String transmission,
                  String color, double mileage, double engineVolume, boolean allWheelDrive) {
        super(model, country, year, transmission, color, mileage, engineVolume);
        this.allWheelDrive = allWheelDrive;
    }

    public boolean isAllWheelDrive() {
        return allWheelDrive;
    }
    public void setAllWheelDrive(boolean allWheelDrive) {
        this.allWheelDrive = allWheelDrive;
    }

    @Override
    public String getFullInfo() {
        return super.getFullInfo() + String.format(", полный привод: %s",
                allWheelDrive ? "да" : "нет");
    }
}