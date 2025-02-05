package altia.cars.demo.application.ports.out;

import altia.cars.demo.domain.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CarRepositoryPort {

    List<Car> findByCarName(String name);
    List<Car> findByCarModel(String model);
    List<Car> findMostExpensiveCars();
    Car save(Car car);
    long countAvailableCars();
    List<Car> findAvailableCars();
    boolean deleteByCarModel(String model);
    Page<Car> findByCriteria(String name, String model, Double minPrice, Double maxPrice, Pageable pageable);
    Optional<Car> findCarById(Long id);

}
