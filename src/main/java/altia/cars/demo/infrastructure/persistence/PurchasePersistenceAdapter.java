package altia.cars.demo.infrastructure.persistence;

import altia.cars.demo.application.ports.out.PurchaseRepositoryPort;
import altia.cars.demo.domain.model.Purchase;
import altia.cars.demo.infrastructure.persistence.entity.PurchaseEntity;
import altia.cars.demo.infrastructure.persistence.mapper.PurchasePersistenceMapper;
import altia.cars.demo.infrastructure.persistence.repository.PurchaseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PurchasePersistenceAdapter implements PurchaseRepositoryPort {
    private final PurchaseRepository repository;
    private final PurchasePersistenceMapper mapper;

    public PurchasePersistenceAdapter(PurchaseRepository repository, PurchasePersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Purchase save(Purchase purchase) {
        PurchaseEntity entity = mapper.toEntity(purchase);
        PurchaseEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Purchase> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Purchase> findByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Purchase> findByListingId(Long listingId) {
        return repository.findByListingId(listingId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}