package altia.cars.demo.application.ports.in;

import altia.cars.demo.domain.model.Car;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface CarServicePort {

    List<Car> getCarsByName(String name);
    List<Car> getAvailableCars();
    List<Car> getCarsByModel(String model);
    Car addCar(Car car);
    List<Car> getMostExpensiveCars();
    boolean deleteCarsByModel(String model);
    long countAvailableCars();
}
