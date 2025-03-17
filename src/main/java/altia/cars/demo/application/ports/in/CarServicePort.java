package altia.cars.demo.application.ports.in;

import altia.cars.demo.domain.model.Car;
import altia.cars.demo.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CarServicePort {

    List<Car> getCarsByBrand(String brand);
    List<Car> getCarsByModel(String model);
    List<Car> getCarsByYear(Integer year);
    Car addCar(Car car);
    Boolean deleteCarsByModel(String model);
    Page<Car> findByCriteria(String brand, String model, Integer year, Pageable pageable);
    Optional<Car> getCarById(Long id);
    Optional<Car> updateCar(Long id, Car car);
    Boolean deleteCarsByIds(List<Long> ids);
    List<Car> findAllCars();

}
