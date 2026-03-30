import Cars.*;

import java.util.ArrayList;
import java.util.List;

import static methods.AddCars.addCars;
import static methods.CarColor.changeGreenToRed;
import static methods.CarsAfter2006.printCarsAfter2006;
import static methods.TransmissionStats.printTransmissionStats;

public class Main {
    //it's Main origin
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        addCars(cars);
        printCarsAfter2006(cars);
        changeGreenToRed(cars);
        printTransmissionStats(cars);
    }
}
