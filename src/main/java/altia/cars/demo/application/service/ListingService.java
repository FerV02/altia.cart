package altia.cars.demo.application.service;

import altia.cars.demo.application.ports.in.ListingServicePort;
import altia.cars.demo.application.ports.out.ListingRepositoryPort;
import altia.cars.demo.domain.model.Listing;
import altia.cars.demo.domain.model.ListingStatus;
import altia.cars.demo.domain.model.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ListingService implements ListingServicePort {
    private final ListingRepositoryPort repository;

    public ListingService(ListingRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Listing createListing(Listing listing) {
        return repository.save(listing);
    }

    @Override
    public Optional<Listing> getListingById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Listing> getAllListings() {
        return repository.findAll();
    }

    @Override
    public Boolean deleteListing(Long id, Long userId) {
        Optional<Listing> listing = repository.findById(id);
        if (listing.isPresent() && listing.get().getUserId().equals(userId)) {
            repository.deleteById(id);
            return true;
        }else {
            throw new IllegalArgumentException("No tienes permiso para eliminar este listing.");
        }
    }

    @Override
    public List<Listing> getListingsByStatus(ListingStatus listingStatus) {
        return repository.findByStatus(listingStatus);
    }

    @Override
    public List<Listing> getListingsByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Listing> getListingsByCarId(Long carId) {
        return repository.findByCarId(carId);
    }

    @Override
    public List<Listing> getListingsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Listing> getListingsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByListingDateBetween(startDate, endDate);
    }

    @Override
    public void updateListingStatus(Long id, ListingStatus listingStatus, Long userId) {
        Optional<Listing> listing = repository.findById(id);
        if (listing.isPresent() && listing.get().getUserId().equals(userId)) {
            repository.updateListingStatus(id, listingStatus);
        } else {
            throw new IllegalArgumentException("No tienes permiso para actualizar el estado de este listing.");
        }
    }

    @Override
    public List<Listing> getListingsByCarBrandAndModel(String brand, String model) {
        return repository.findByCarBrandAndModel(brand, model);
    }

    @Override
    public List<Listing> getListingsByUserRole(UserRole userRole) {
        return repository.findByUserRole(userRole);
    }

    @Override
    public Page<Listing> findByCriteria(
            ListingStatus status,
            Long userId,
            Long carId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable) {
        return repository.findByCriteria(status, userId, carId, minPrice, maxPrice, startDate, endDate, pageable);
    }
}