package altia.cars.demo.infrastructure.rest.response;
import altia.cars.demo.domain.model.Listing;
import altia.cars.demo.domain.model.ListingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ListingResponse {
    private Long listingId;
    private Long carId;
    private Long userId;
    private BigDecimal listingPrice;
    private ListingStatus listingStatus;
    private LocalDateTime listingDate;
    private String listingDescription;


    public ListingResponse (Listing listing) {
        this.listingId = listing.getId();
        this.carId =listing.getCarId();
        this.userId =listing.getUserId();
        this.listingPrice = listing.getListingPrice();
        this.listingStatus =listing.getListingStatus();
        this.listingDate = listing.getListingDate();
        this.listingDescription = listing.getListingDescription();

    }
    public ListingResponse(Long carId, Long userId, Long id, BigDecimal listingPrice, ListingStatus listingStatus, LocalDateTime listingDate, String listingDescription) {
        this.carId = carId;
        this.userId = userId;
        this.listingPrice = listingPrice;
        this.listingStatus = listingStatus;
        this.listingDate = listingDate;
        this.listingDescription = listingDescription;
    }

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
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