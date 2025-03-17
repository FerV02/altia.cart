package altia.cars.demo.infrastructure.persistence.repository;

import altia.cars.demo.infrastructure.persistence.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    @Query("SELECT p FROM PurchaseEntity p WHERE p.userId = :userId")
    List<PurchaseEntity> findByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM PurchaseEntity p WHERE p.listingId = :listingId")
    List<PurchaseEntity> findByListingId(@Param("listingId") Long listingId);
}