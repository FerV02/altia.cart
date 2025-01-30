package altia.cars.demo.infrastructure.persistence.mapper;

import altia.cars.demo.domain.model.Car;
import altia.cars.demo.infrastructure.persistence.entity.CarEntity;
import org.springframework.stereotype.Component;

@Component
public class CarPersistenceMapper {

    public Car toDomain(CarEntity carEntity) {
        if (carEntity == null) {
            return null;
        }

        return new Car(
                carEntity.getId(),
                carEntity.getCarName(),
                carEntity.getCarModel(),
                carEntity.getCarDescription(),
                carEntity.getCarPrice(),
                carEntity.isCarAvailable()
        );
    }

    public CarEntity toEntity(Car car) {
        if (car == null) {
            return null;
        }

        CarEntity carEntity = new CarEntity();
        carEntity.setId(car.getId());
        carEntity.setCarName(car.getCarName());
        carEntity.setCarModel(car.getCarModel());
        carEntity.setCarDescription(car.getCarDescription());
        carEntity.setCarPrice(car.getCarPrice());
        carEntity.setCarAvailable(car.isCarAvailable());
        return carEntity;
    }
}
