package altia.cars.demo.infrastructure.rest.response;

import altia.cars.demo.domain.model.Purchase;

import java.time.LocalDateTime;

public class PurchaseResponse {
    private Long id;
    private Long userId;
    private Long listingId;
    private LocalDateTime purchaseDate;

    public PurchaseResponse(Purchase purchase) {
        this.id = purchase.getId();
        this.userId = purchase.getUserId();
        this.listingId = purchase.getListingId();
        this.purchaseDate = purchase.getPurchaseDate();
    }


    public PurchaseResponse(Long id, Long userId, Long listingId, LocalDateTime purchaseDate) {
        this.id = id;
        this.userId = userId;
        this.listingId = listingId;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}