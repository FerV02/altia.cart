package altia.cars.demo.application.service;

import altia.cars.demo.application.ports.in.PurchaseServicePort;
import altia.cars.demo.application.ports.out.PurchaseRepositoryPort;
import altia.cars.demo.domain.model.Purchase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService implements PurchaseServicePort {
    private final PurchaseRepositoryPort repository;

    public PurchaseService(PurchaseRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Purchase createPurchase(Purchase purchase) {
        List<Purchase> existingPurchases = repository.findByListingId(purchase.getListingId());
        if (!existingPurchases.isEmpty()) {
            throw new IllegalArgumentException("El listing ya ha sido comprado.");
        }
        return repository.save(purchase);
    }

    @Override
    public Optional<Purchase> getPurchaseById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Purchase> getPurchasesByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Purchase> getPurchasesByListing(Long listingId) {
        return repository.findByListingId(listingId);
    }

    @Override
    public void deletePurchase(Long id, Long userId) {
        Optional<Purchase> purchase = repository.findById(id);
        if (purchase.isPresent() && purchase.get().getUserId().equals(userId)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No tienes permiso para eliminar esta compra.");
        }
    }
}