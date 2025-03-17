package altia.cars.demo.infrastructure.rest.request;

import java.time.LocalDateTime;

public class FavoritesRequest {
    private Long userId;
    private Long listingId;
    private LocalDateTime favoriteDate;

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

    public LocalDateTime getFavoriteDate() {
        return favoriteDate;
    }

    public void setFavoriteDate(LocalDateTime favoriteDate) {
        this.favoriteDate = favoriteDate;
    }
}