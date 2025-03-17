package altia.cars.demo.infrastructure.persistence.repository;

import altia.cars.demo.domain.model.ListingStatus;
import altia.cars.demo.domain.model.UserRole;
import altia.cars.demo.infrastructure.persistence.entity.ListingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<ListingEntity, Long> {
    @Query("SELECT l FROM ListingEntity l WHERE l.listingStatus = :status")
    List<ListingEntity> findByListingStatus(@Param("status") ListingStatus status);

    @Query("SELECT l FROM ListingEntity l WHERE l.user.id = :userId")
    List<ListingEntity> findByUserId(@Param("userId") Long userId);

    @Query("SELECT l FROM ListingEntity l WHERE l.car.id = :carId")
    List<ListingEntity> findByCarId(@Param("carId") Long carId);

    @Query("SELECT l FROM ListingEntity l WHERE l.listingPrice BETWEEN :minPrice AND :maxPrice")
    List<ListingEntity> findByListingPriceBetween(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    @Query("SELECT l FROM ListingEntity l WHERE l.listingDate BETWEEN :startDate AND :endDate")
    List<ListingEntity> findByListingDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Modifying
    @Query("UPDATE ListingEntity l SET l.listingStatus = :status WHERE l.id = :id")
    void updateListingStatus(@Param("id") Long id, @Param("status") ListingStatus status);

    @Query("SELECT l FROM ListingEntity l WHERE l.car.carBrand = :brand AND l.car.carModel = :model")
    List<ListingEntity> findByCarBrandAndModel(@Param("brand") String brand, @Param("model") String model);

    @Query("SELECT l FROM ListingEntity l WHERE l.user.userRole = :role")
    List<ListingEntity> findByUserRole(@Param("role") UserRole role);

    @Query("SELECT l FROM ListingEntity l WHERE " +
            "(:status IS NULL OR l.listingStatus = :status) AND " +
            "(:userId IS NULL OR l.user.id = :userId) AND " +
            "(:carId IS NULL OR l.car.id = :carId) AND " +
            "(:minPrice IS NULL OR l.listingPrice >= :minPrice) AND " +
            "(:maxPrice IS NULL OR l.listingPrice <= :maxPrice) AND " +
            "(:startDate IS NULL OR l.listingDate >= :startDate) AND " +
            "(:endDate IS NULL OR l.listingDate <= :endDate)")
    Page<ListingEntity> findByCriteria(
            @Param("status") ListingStatus status,
            @Param("userId") Long userId,
            @Param("carId") Long carId,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable);
}
