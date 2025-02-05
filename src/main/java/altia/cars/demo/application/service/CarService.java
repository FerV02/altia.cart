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
    public List<Car> getCarsByName(String name) {
        return carRepositoryPort.findByCarName(name);
    }

    @Override
    public List<Car> getCarsByModel(String model) {
        return carRepositoryPort.findByCarModel(model);
    }

    @Override
    public List<Car> getMostExpensiveCars() {
        return carRepositoryPort.findMostExpensiveCars();
    }

    @Override
    public long countAvailableCars() {
        return carRepositoryPort.countAvailableCars();
    }

    @Override
    public List<Car> getAvailableCars() {
        return carRepositoryPort.findAvailableCars();
    }

    @Override
    public boolean deleteCarsByModel(String model) {
        return carRepositoryPort.deleteByCarModel(model);
    }

    @Override
    public Page<Car> findByCriteria(String name, String model, Double minPrice, Double maxPrice, Pageable pageable) {
        return carRepositoryPort.findByCriteria(name, model, minPrice, maxPrice, pageable);
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepositoryPort.findCarById(id);
    }
}

