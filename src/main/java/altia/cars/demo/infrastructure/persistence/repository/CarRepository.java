package altia.cars.demo.infrastructure.persistence.repository;

import altia.cars.demo.infrastructure.persistence.entity.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT c FROM CarEntity c " +
            "WHERE (:name IS NULL OR c.carName LIKE %:name%) " +
            "AND (:model IS NULL OR c.carModel = :model) " +
            "AND (:minPrice IS NULL OR c.carPrice >= :minPrice) " +
            "AND (:maxPrice IS NULL OR c.carPrice <= :maxPrice)")
    Page<CarEntity> findByCriteria(
            @Param("name") String name,
            @Param("model") String model,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable);
}
