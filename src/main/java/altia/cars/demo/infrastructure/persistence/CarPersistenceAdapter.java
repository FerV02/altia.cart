package altia.cars.demo.infrastructure.persistence;

import altia.cars.demo.application.ports.out.CarRepositoryPort;
import altia.cars.demo.domain.model.Car;
import altia.cars.demo.infrastructure.persistence.entity.CarEntity;
import altia.cars.demo.infrastructure.persistence.mapper.CarPersistenceMapper;
import altia.cars.demo.infrastructure.persistence.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CarPersistenceAdapter implements CarRepositoryPort {

    private final CarRepository carRepository;
    private final CarPersistenceMapper carPersistenceMapper;

    public CarPersistenceAdapter(CarRepository carRepository, CarPersistenceMapper carPersistenceMapper) {
        this.carRepository = carRepository;
        this.carPersistenceMapper = carPersistenceMapper;
    }

    @Override
    public Car save(Car car) {
        CarEntity carEntity = carPersistenceMapper.toEntity(car);
        return carPersistenceMapper.toDomain(carRepository.save(carEntity));
    }

    @Override
    public List<Car> findByCarName(String name) {
        return carRepository.findByCarName(name)
                .stream()
                .map(carPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findByCarModel(String model) {
        return carRepository.findByCarModel(model)
                .stream()
                .map(carPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> findMostExpensiveCars() {
        return carRepository.findAll(Sort.by(Sort.Direction.DESC, "carPrice"))
                .stream()
                .map(carPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long countAvailableCars() {
        return carRepository.countAvailableCars();
    }

    @Override
    public List<Car> findAvailableCars() {
        return carRepository.findAvailableCars()
                .stream()
                .map(carPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteByCarModel(String model) {
        return carRepository.deleteByCarModel(model) != null;
    }

    @Override
    public Page<Car> findByCriteria(String name, String model, Double minPrice, Double maxPrice, Pageable pageable) {
        Page<CarEntity> carEntities = carRepository.findByCriteria(name, model, minPrice, maxPrice, pageable);
        return carEntities.map(carPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Car> findCarById(Long id) {
        return carRepository.findById(id).map(carPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Car> updateCar(Long id, Car car) {
        return carRepository.findById(id).map(existingCarEntity -> {
            if (car.getCarName() != null) {
                existingCarEntity.setCarName(car.getCarName());
            }
            if (car.getCarModel() != null) {
                existingCarEntity.setCarModel(car.getCarModel());
            }
            if (car.getCarDescription() != null) {
                existingCarEntity.setCarDescription(car.getCarDescription());
            }
            if (car.getCarPrice() != null) {
                existingCarEntity.setCarPrice(car.getCarPrice());
            }
            if (car.getCarAvailable() != null) {
                existingCarEntity.setCarAvailable(car.getCarAvailable());
            }

            CarEntity savedCarEntity = carRepository.save(existingCarEntity);
            return carPersistenceMapper.toDomain(savedCarEntity);
        });
    }

}
