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
                carEntity.getCarBrand(),
                carEntity.getCarModel(),
                carEntity.getCarYear(),
                carEntity.getCarDescription()
        );
    }

    public CarEntity toEntity(Car car) {
        if (car == null) {
            return null;
        }

        CarEntity carEntity = new CarEntity();
        carEntity.setId(car.getId());
        carEntity.setCarBrand(car.getCarBrand());
        carEntity.setCarModel(car.getCarModel());
        carEntity.setCarYear(car.getCarYear());
        carEntity.setCarDescription(car.getCarDescription());
        return carEntity;
    }
}
