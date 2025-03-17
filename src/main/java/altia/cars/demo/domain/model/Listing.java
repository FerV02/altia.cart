package altia.cars.demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Listing {
    private Long id;
    private Long carId;
    private Long userId;
    private BigDecimal listingPrice;
    private ListingStatus listingStatus;
    private LocalDateTime listingDate;
    private String listingDescription;

    public Listing(Long id, Long carId, Long userId, BigDecimal listingPrice, ListingStatus listingStatus, LocalDateTime listingDate, String listingDescription) {
        this.id = id;
        this.carId = carId;
        this.userId = userId;
        this.listingPrice = listingPrice;
        this.listingStatus = listingStatus;
        this.listingDate = listingDate;
        this.listingDescription = listingDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(BigDecimal listingPrice) {
        this.listingPrice = listingPrice;
    }

    public ListingStatus getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(ListingStatus listingStatus) {
        this.listingStatus = listingStatus;
    }

    public LocalDateTime getListingDate() {
        return listingDate;
    }

    public void setListingDate(LocalDateTime listingDate) {
        this.listingDate = listingDate;
    }

    public String getListingDescription() {
        return listingDescription;
    }

    public void setListingDescription(String listingDescription) {
        this.listingDescription = listingDescription;
    }
}
