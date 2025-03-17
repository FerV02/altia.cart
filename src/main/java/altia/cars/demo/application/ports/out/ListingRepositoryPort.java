package altia.cars.demo.application.ports.out;

import altia.cars.demo.domain.model.Listing;
import altia.cars.demo.domain.model.ListingStatus;
import altia.cars.demo.domain.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ListingRepositoryPort {
    Listing save(Listing listing);
    Optional<Listing> findById(Long id);
    List<Listing> findAll();
    void deleteById(Long id);

    List<Listing> findByStatus(ListingStatus listingStatus);
    List<Listing> findByUserId(Long userId);
    List<Listing> findByCarId(Long carId);
    List<Listing> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<Listing> findByListingDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    void updateListingStatus(Long id, ListingStatus listingStatus);
    List<Listing> findByCarBrandAndModel(String brand, String model);
    List<Listing> findByUserRole(UserRole userRole);
    Page<Listing> findByCriteria(ListingStatus status, Long userId, Long carId, BigDecimal minPrice, BigDecimal maxPrice, LocalDateTime startDate,
            LocalDateTime endDate, Pageable pageable);
}