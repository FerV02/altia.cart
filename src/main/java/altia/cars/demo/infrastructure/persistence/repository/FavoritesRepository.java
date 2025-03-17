package altia.cars.demo.infrastructure.persistence.repository;

import altia.cars.demo.infrastructure.persistence.entity.FavoritesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository extends JpaRepository<FavoritesEntity, Long> {
    @Query("SELECT f FROM FavoritesEntity f WHERE f.userId = :userId")
    List<FavoritesEntity> findByUserId(@Param("userId") Long userId);

    @Query("SELECT f FROM FavoritesEntity f WHERE f.listingId = :listingId")
    List<FavoritesEntity> findByListingId(@Param("listingId") Long listingId);
}