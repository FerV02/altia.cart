package altia.cars.demo.application.ports.in;

import altia.cars.demo.domain.model.Purchase;
import java.util.List;
import java.util.Optional;

public interface PurchaseServicePort {
    Purchase createPurchase(Purchase purchase);
    Optional<Purchase> getPurchaseById(Long id);
    List<Purchase> getPurchasesByUser(Long userId);
    List<Purchase> getPurchasesByListing(Long listingId);
    void deletePurchase(Long id, Long userId);
}