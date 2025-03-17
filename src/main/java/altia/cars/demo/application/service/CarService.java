package altia.cars.demo.application.service;

import altia.cars.demo.application.ports.in.CarServicePort;
import altia.cars.demo.application.ports.out.CarRepositoryPort;
import altia.cars.demo.domain.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServicePort {

    private final CarRepositoryPort carRepositoryPort;

    public CarService(CarRepositoryPort carRepositoryPort) {
        this.carRepositoryPort = carRepositoryPort;
    }

    @Override
    public Car addCar(Car car) {
        return carRepositoryPort.save(car);
    }

    @Override
    public List<Car> getCarsByBrand(String brand) {
        return carRepositoryPort.findByCarBrand(brand);
    }

    @Override
    public List<Car> getCarsByModel(String model) {
        return carRepositoryPort.findByCarModel(model);
    }

    @Override
    public List<Car> getCarsByYear(Integer year) {
        return carRepositoryPort.findByCarYear(year);
    }

    @Override
    public Boolean deleteCarsByModel(String model) {
        return carRepositoryPort.deleteByCarModel(model);
    }

    @Override
    public Page<Car> findByCriteria(String name, String model, Integer year, Pageable pageable) {
        return carRepositoryPort.findByCriteria(name, model, year, pageable);
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepositoryPort.findCarById(id);
    }
    @Override
    public Optional<Car> updateCar(Long id, Car car) {
        return carRepositoryPort.updateCar(id, car);
    }

    @Override
    public Boolean deleteCarsByIds(List<Long> ids) {
        return carRepositoryPort.deleteByCarIds(ids);
    }
}

