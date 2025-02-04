package altia.cars.demo.infrastructure.persistence.repository;

import altia.cars.demo.infrastructure.persistence.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {


    @Query("SELECT c FROM CarEntity c WHERE c.carName = :search")
    List<CarEntity> findByCarName(String search);

    @Query("SELECT c FROM CarEntity c WHERE c.carModel = :search")
    List<CarEntity> findByCarModel(String search);

    @Query("SELECT COUNT(c) FROM CarEntity c WHERE c.carAvailable = true")
    long countAvailableCars();

    @Query("SELECT c FROM CarEntity c WHERE c.carAvailable = true")
    List<CarEntity> findAvailableCars();

    @Query("DELETE FROM CarEntity c WHERE c.carModel = :model")
    String deleteByCarModel(String model);




}
