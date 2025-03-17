package altia.cars.demo.infrastructure.persistence;

import altia.cars.demo.application.ports.out.ListingRepositoryPort;
import altia.cars.demo.domain.model.Listing;
import altia.cars.demo.domain.model.ListingStatus;
import altia.cars.demo.domain.model.UserRole;
import altia.cars.demo.infrastructure.persistence.mapper.ListingPersistanceMapper;
import altia.cars.demo.infrastructure.persistence.repository.ListingRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ListingPersistenceAdapter implements ListingRepositoryPort {
    private final ListingRepository repository;
    private final ListingPersistanceMapper mapper;

    public ListingPersistenceAdapter(ListingRepository repository, ListingPersistanceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Listing save(Listing listing) {
        return mapper.toDomain(repository.save(mapper.toEntity(listing)));
    }

    @Override
    public Optional<Listing> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Listing> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Listing> findByStatus(ListingStatus listingStatus) {
        return repository.findByListingStatus(listingStatus).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Listing> findByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Listing> findByCarId(Long carId) {
        return repository.findByCarId(carId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Listing> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.findByListingPriceBetween(minPrice, maxPrice).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Listing> findByListingDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByListingDateBetween(startDate, endDate).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void updateListingStatus(Long id, ListingStatus listingStatus) {
        repository.updateListingStatus(id, listingStatus);
    }

    @Override
    public List<Listing> findByCarBrandAndModel(String brand, String model) {
        return repository.findByCarBrandAndModel(brand, model).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Listing> findByUserRole(UserRole userRole) {
        return repository.findByUserRole(userRole).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
    @Override
    public Page<Listing> findByCriteria(ListingStatus status, Long userId, Long carId, BigDecimal minPrice, BigDecimal maxPrice, LocalDateTime startDate,
            LocalDateTime endDate, Pageable pageable) {
        return repository.findByCriteria(status, userId, carId, minPrice, maxPrice, startDate, endDate, pageable)
                .map(mapper::toDomain);
    }
}