package altia.cars.demo.application.ports.out;

import altia.cars.demo.domain.model.Purchase;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepositoryPort {
    Purchase save(Purchase purchase);
    Optional<Purchase> findById(Long id);
    List<Purchase> findByUserId(Long userId);
    List<Purchase> findByListingId(Long listingId);
    void deleteById(Long id);
}