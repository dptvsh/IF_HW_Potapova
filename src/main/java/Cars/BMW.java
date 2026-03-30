package Cars;

public class BMW extends Car {
    private String series;

    public BMW(String model, String country, int year, String transmission,
               String color, double mileage, double engineVolume, String series) {
        super(model, country, year, transmission, color, mileage, engineVolume);
        this.series = series;
    }

    public String getSeries() {
        return series;
    }
    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public String getFullInfo() {
        return super.getFullInfo() + String.format(", серия: %s", series);
    }
}
