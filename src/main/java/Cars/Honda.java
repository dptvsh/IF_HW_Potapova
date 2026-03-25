package Cars;

public class Honda extends Car {
    private String bodyType;

    public Honda(String model, String country, int year, String transmission,
                 String color, double mileage, double engineVolume, String bodyType) {
        super(model, country, year, transmission, color, mileage, engineVolume);
        this.bodyType = bodyType;
    }

    public String getBodyType() {
        return bodyType;
    }
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public String getFullInfo() {
        return super.getFullInfo() + String.format(", тип кузова: %s", bodyType);
    }
}