package altia.cars.demo.application.ports.out;

import altia.cars.demo.domain.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CarRepositoryPort {

    List<Car> findByCarBrand(String brand);
    List<Car> findByCarModel(String model);
    List<Car> findByCarYear(Integer year);
    Car save(Car car);
    Boolean deleteByCarModel(String model);
    Page<Car> findByCriteria(String name, String model, Integer year, Pageable pageable);
    Optional<Car> findCarById(Long id);
    Optional<Car> updateCar(Long id, Car car);
    Boolean deleteByCarIds(List<Long> ids);

}
