package Cars;

public abstract class Car {
    private final String model;
    private final String country;
    private final int year;
    private final String transmission;
    private String color;
    private double mileage;
    private final double engineVolume;

    public Car(String model, String country, int year, String transmission,
               String color, double mileage, double engineVolume) {
        this.model = model;
        this.country = country;
        this.year = year;
        this.transmission = transmission;
        this.color = color;
        this.mileage = mileage;
        this.engineVolume = engineVolume;
    }

    public String getModel() {
        return model;
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public double getMileage() {
        return mileage;
    }
    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public String getFullInfo() {
        return String.format(
                "%s %d, %s, %s, цвет: %s, пробег: %.0f км, объём двигателя: %.1f л",
                model, year, country, transmission, color, mileage, engineVolume);
    }

    public void validateColor(String newColor) {
        if (newColor == null) {
            throw new IllegalArgumentException("Цвет не может быть null!");
        }
        setColor(newColor);
    }
}
