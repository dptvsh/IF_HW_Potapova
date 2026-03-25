package Cars;

public class Ford extends Car {
    private boolean isPickup;

    public Ford(String model, String country, int year, String transmission,
                String color, double mileage, double engineVolume, boolean isPickup) {
        super(model, country, year, transmission, color, mileage, engineVolume);
        this.isPickup = isPickup;
    }

    public boolean isPickup() {
        return isPickup;
    }
    public void setPickup(boolean pickup) {
        isPickup = pickup;
    }

    @Override
    public String getFullInfo() {
        return super.getFullInfo() + String.format(", пикап: %s",
                isPickup ? "да" : "нет");
    }
}