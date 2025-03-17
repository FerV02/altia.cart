package altia.cars.demo.infrastructure.persistence;

import altia.cars.demo.application.ports.out.CarRepositoryPort;
import altia.cars.demo.domain.model.Car;
import altia.cars.demo.infrastructure.persistence.entity.CarEntity;
import altia.cars.demo.infrastructure.persistence.mapper.CarPersistenceMapper;
import altia.cars.demo.infrastructure.persistence.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        try {
            CarEntity carEntity = carPersistenceMapper.toEntity(car);
            return carPersistenceMapper.toDomain(carRepository.save(carEntity));
        } catch (Exception e) {
            throw new PersistenceException("Error saving car", e);
        }
    }

    @Override
    public List<Car> findByCarBrand(String brand) {
        try {
            return carRepository.findByCarBrand(brand)
                    .stream()
                    .map(carPersistenceMapper::toDomain)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new PersistenceException("Error finding cars by name", e);
        }
    }

    @Override
    public List<Car> findByCarModel(String model) {
        try {
            return carRepository.findByCarModel(model)
                    .stream()
                    .map(carPersistenceMapper::toDomain)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new PersistenceException("Error finding cars by model", e);
        }
    }

    @Override
    public List<Car> findByCarYear(Integer year) {
        try {
            return carRepository.findByCarYear(year)
                    .stream()
                    .map(carPersistenceMapper::toDomain)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new PersistenceException("Error finding cars by year", e);
        }
    }

    @Override
    public Boolean deleteByCarModel(String model) {
        try {
            return carRepository.deleteByCarModel(model) > 0;
        } catch (Exception e) {
            throw new PersistenceException("Error deleting cars by model", e);
        }
    }

    @Override
    public Page<Car> findByCriteria(String name, String model, Integer year, Pageable pageable) {
        try {
            Page<CarEntity> carEntities = carRepository.findByCriteria(name, model, year, pageable);
            return carEntities.map(carPersistenceMapper::toDomain);
        } catch (Exception e) {
            throw new PersistenceException("Error finding cars by criteria", e);
        }
    }

    @Override
    public Optional<Car> findCarById(Long id) {
        try {
            return carRepository.findById(id).map(carPersistenceMapper::toDomain);
        } catch (Exception e) {
            throw new PersistenceException("Error finding car by ID", e);
        }
    }

    @Override
    public Optional<Car> updateCar(Long id, Car car) {
        try {
            return carRepository.findById(id).map(existingCarEntity -> {
                if (car.getCarBrand() != null) {
                    existingCarEntity.setCarBrand(car.getCarBrand());
                }
                if (car.getCarModel() != null) {
                    existingCarEntity.setCarModel(car.getCarModel());
                }
                if (car.getCarYear() != null) {
                    existingCarEntity.setCarYear(car.getCarYear());
                }
                if (car.getCarDescription() != null) {
                    existingCarEntity.setCarDescription(car.getCarDescription());
                }
                CarEntity savedCarEntity = carRepository.save(existingCarEntity);
                return Optional.of(carPersistenceMapper.toDomain(savedCarEntity));
            }).orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + id));
        } catch (Exception e) {
            throw new PersistenceException("Error updating car", e);
        }
    }


    @Override
    public Boolean deleteByCarIds(List<Long> ids) {
        try {
            carRepository.deleteAllById(ids);
            return true;
        } catch (EmptyResultDataAccessException ex) {
            return false;
        } catch (Exception e) {
            throw new PersistenceException("Error deleting cars by IDs", e);
        }
    }
}
