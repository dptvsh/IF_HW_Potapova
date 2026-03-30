package methods;

import Cars.Car;

import java.util.List;

public class CarColor {
    public static void changeGreenToRed(List<Car> cars) {
        for (Car car : cars) {
            if ("зеленый".equalsIgnoreCase(car.getColor())) {
                car.validateColor("красный");
                System.out.format(
                        "Цвет автомобиля %s %d изменен с зеленого на красный.\n",
                        car.getModel(), car.getYear()
                );
            }
        }
    }
}
