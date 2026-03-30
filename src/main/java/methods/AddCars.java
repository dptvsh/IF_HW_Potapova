package methods;

import Cars.*;

import java.util.List;

public class AddCars {
    public static void addCars(List<Car> cars) {

        cars.add(new Suzuki("Suzuki Vitara", "Япония", 2015,
                "АКПП", "зеленый", 45000,
                1.6, true));
        cars.add(new Suzuki("Suzuki Swift", "Япония", 2006,
                "МКПП", "синий", 120000,
                1.3, false));

        cars.add(new Toyota("Toyota Camry", "Япония", 2020,
                "АКПП", "черный", 20000,
                2.5, false));
        cars.add(new Toyota("Toyota Prius", "Япония", 2008,
                "АКПП", "зеленый", 80000,
                1.8, true));

        cars.add(new Honda("Honda CR-V", "Япония",2018,
                "АКПП", "белый", 60000,
                2.0,  "SUV"));
        cars.add(new Honda("Honda Civic", "Япония",2003,
                "МКПП", "красный", 180000,
                1.6,  "седан"));

        cars.add(new Ford("Ford Focus", "США", 2012,
                "МКПП", "зеленый", 110000,
                1.6, false));
        cars.add(new Ford("Ford F-150", "США", 2019,
                "АКПП", "серебристый", 30000,
                5.0, true));

        cars.add(new BMW("BMW X5", "Германия", 2007,
                "АКПП", "черный", 150000,
                3.0, "X5"));
        cars.add(new BMW("BMW 3 Series", "Германия", 2014,
                "МКПП", "синий", 90000,
                2.0, "3 Series"));
    }
}
