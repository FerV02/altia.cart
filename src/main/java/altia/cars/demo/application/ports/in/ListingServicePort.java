package altia.cars.demo.application.ports.in;

import altia.cars.demo.domain.model.Listing;
import altia.cars.demo.domain.model.ListingStatus;
import altia.cars.demo.domain.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ListingServicePort {
    Listing createListing(Listing listing);
    Optional<Listing> getListingById(Long id);
    List<Listing> getAllListings();
    Boolean deleteListing(Long id, Long userId);

    List<Listing> getListingsByStatus(ListingStatus listingStatus);
    List<Listing> getListingsByUserId(Long userId);
    List<Listing> getListingsByCarId(Long carId);
    List<Listing> getListingsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Listing> getListingsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    void updateListingStatus(Long id, ListingStatus listingStatus, Long userId);
    List<Listing> getListingsByCarBrandAndModel(String brand, String model);
    List<Listing> getListingsByUserRole(UserRole userRole);
    Page<Listing> findByCriteria(ListingStatus status, Long userId, Long carId, BigDecimal minPrice, BigDecimal maxPrice, LocalDateTime startDate,
                                 LocalDateTime endDate, Pageable pageable);
}