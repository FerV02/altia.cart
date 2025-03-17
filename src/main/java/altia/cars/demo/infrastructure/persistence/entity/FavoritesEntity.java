package altia.cars.demo.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorites")
public class FavoritesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "listing_id", nullable = false)
    private Long listingId;

    @Column(name = "favorite_date", nullable = false)
    private LocalDateTime favoriteDate;

    public FavoritesEntity() {}

    public FavoritesEntity(Long id, Long userId, Long listingId, LocalDateTime favoriteDate) {
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