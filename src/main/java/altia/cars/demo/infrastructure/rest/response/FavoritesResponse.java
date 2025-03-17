package altia.cars.demo.infrastructure.rest.response;

import altia.cars.demo.domain.model.Favorites;

import java.time.LocalDateTime;

public class FavoritesResponse {
    private Long id;
    private Long userId;
    private Long listingId;
    private LocalDateTime favoriteDate;

    public FavoritesResponse(Favorites favorites) {
        this.id = favorites.getId();
        this.userId = favorites.getUserId();
        this.listingId = favorites.getListingId();
        this.favoriteDate = favorites.getFavoriteDate();
    }


    public FavoritesResponse(Long id, Long userId, Long listingId, LocalDateTime favoriteDate) {
        this.id = id;
        this.userId = userId;
        this.listingId = listingId;
        this.favoriteDate = favoriteDate;
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

    public LocalDateTime getFavoriteDate() {
        return favoriteDate;
    }

    public void setFavoriteDate(LocalDateTime favoriteDate) {
        this.favoriteDate = favoriteDate;
    }
}