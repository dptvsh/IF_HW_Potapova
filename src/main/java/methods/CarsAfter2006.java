package methods;

import Cars.Car;

import java.util.List;

public class CarsAfter2006 {
    public static void printCarsAfter2006(List<Car> cars) {
        for (Car car : cars) {
            if (car.getYear() > 2006) {
                System.out.println(car.getFullInfo());
            } else {
                System.out.println("Устаревший авто: " + car.getModel() + " " + car.getYear());
            }
        }
    }
}
