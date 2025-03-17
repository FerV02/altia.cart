package altia.cars.demo.domain.model;

import java.time.LocalDateTime;

public class Purchase {
    private Long id;
    private Long userId;
    private Long listingId;
    private LocalDateTime purchaseDate;

    public Purchase(Long id, Long userId, Long listingId, LocalDateTime purchaseDate) {
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