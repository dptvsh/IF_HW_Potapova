package methods;

import Cars.Car;

import java.util.List;

public class TransmissionStats {
    public static void printTransmissionStats(List<Car> cars) {
        int manual = 0;
        int automatic = 0;
        for (Car car : cars) {
            if ("МКПП".equalsIgnoreCase(car.getTransmission())) {
                manual++;
            } else if ("АКПП".equalsIgnoreCase(car.getTransmission())) {
                automatic++;
            }
        }
        System.out.println(
                "Статистика по коробкам передач:" +
                        "\nМКПП: " + manual + " автомобилей" +
                        "\nАКПП: " + automatic + " автомобилей"
        );
    }
}
