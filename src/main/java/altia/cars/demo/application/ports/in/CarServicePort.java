package altia.cars.demo.application.ports.in;

import altia.cars.demo.domain.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface CarServicePort {

    List<Car> getCarsByName(String name);
    List<Car> getAvailableCars();
    List<Car> getCarsByModel(String model);
    Car addCar(Car car);
    List<Car> getMostExpensiveCars();
    Boolean deleteCarsByModel(String model);
    long countAvailableCars();
    Page<Car> findByCriteria(String name, String model, Double minPrice, Double maxPrice, Pageable pageable);
    Optional<Car> getCarById(Long id);
    Optional<Car> updateCar(Long id, Car car);

}
