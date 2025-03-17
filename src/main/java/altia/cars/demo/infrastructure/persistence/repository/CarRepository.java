package altia.cars.demo.infrastructure.persistence.repository;

import altia.cars.demo.infrastructure.persistence.entity.CarEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {


    @Query("SELECT c FROM CarEntity c WHERE c.carBrand = :search")
    List<CarEntity> findByCarBrand(String search);

    @Query("SELECT c FROM CarEntity c WHERE c.carModel = :search")
    List<CarEntity> findByCarModel(String search);

    @Query("SELECT c FROM CarEntity c WHERE c.carYear = :search")
    List<CarEntity> findByCarYear(Integer search);

    @Modifying
    @Transactional
    @Query("DELETE FROM CarEntity c WHERE c.carModel = :model")
    int deleteByCarModel(@Param("model") String model);

    @Query("SELECT c FROM CarEntity c " +
            "WHERE (:brand IS NULL OR c.carBrand LIKE %:brand%) " +
            "AND (:model IS NULL OR c.carModel = :model) " +
            "AND (:year IS NULL OR c.carYear = :year) " )
    Page<CarEntity> findByCriteria(
            @Param("brand") String brand,
            @Param("model") String model,
            @Param("year") Integer year,
            Pageable pageable);
}
