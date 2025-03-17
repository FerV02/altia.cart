package altia.cars.demo.infrastructure.rest;

import altia.cars.demo.application.ports.in.PurchaseServicePort;
import altia.cars.demo.domain.model.Purchase;
import altia.cars.demo.infrastructure.rest.request.PurchaseRequest;
import altia.cars.demo.infrastructure.rest.response.PurchaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchases")
public class PurchaseRestAdapter {
    private final PurchaseServicePort purchaseService;

    public PurchaseRestAdapter(PurchaseServicePort purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseResponse createPurchase(@RequestBody PurchaseRequest request) {
        LocalDateTime purchaseDate = request.getPurchaseDate() != null ? request.getPurchaseDate() : LocalDateTime.now();
        Purchase purchase = new Purchase(
                null,
                request.getUserId(),
                request.getListingId(),
                purchaseDate
        );
        Purchase createdPurchase = purchaseService.createPurchase(purchase);
        return new PurchaseResponse(
                createdPurchase.getId(),
                createdPurchase.getUserId(),
                createdPurchase.getListingId(),
                createdPurchase.getPurchaseDate()
        );
    }

    @GetMapping("/{id}")
    public PurchaseResponse getPurchaseById(@PathVariable Long id) {
        Purchase purchase = purchaseService.getPurchaseById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));
        return new PurchaseResponse(
                purchase.getId(),
                purchase.getUserId(),
                purchase.getListingId(),
                purchase.getPurchaseDate()
        );
    }

    @GetMapping("/user/{userId}")
    public List<PurchaseResponse> getPurchasesByUser(@PathVariable Long userId) {
        return purchaseService.getPurchasesByUser(userId).stream()
                .map(PurchaseResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/listing/{listingId}")
    public List<PurchaseResponse> getPurchasesByListing(@PathVariable Long listingId) {
        return purchaseService.getPurchasesByListing(listingId).stream()
                .map(PurchaseResponse::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePurchase(@PathVariable Long id, @RequestParam Long userId) {
        purchaseService.deletePurchase(id, userId);
    }
}