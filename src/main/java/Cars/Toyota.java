package Cars;

public class Toyota extends Car {
    private boolean hybrid;

    public Toyota(String model, String country, int year, String transmission,
                  String color, double mileage, double engineVolume, boolean hybrid) {
        super(model, country, year, transmission, color, mileage, engineVolume);
        this.hybrid = hybrid;
    }

    public boolean isHybrid() {
        return hybrid;
    }
    public void setHybrid(boolean hybrid) {
        this.hybrid = hybrid;
    }

    @Override
    public String getFullInfo() {
        return super.getFullInfo() + String.format(", гибрид: %s",
                hybrid ? "да" : "нет");
    }
}
