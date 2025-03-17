package altia.cars.demo.infrastructure.persistence.entity;

import altia.cars.demo.domain.model.ListingStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "listings")
public class ListingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "listing_price", nullable = false)
    private BigDecimal listingPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "listing_status", nullable = false)
    private ListingStatus listingStatus;

    @Column(name = "listing_date", nullable = false)
    private LocalDateTime listingDate;

    @Column(name = "listing_description")
    private String listingDescription;

    public ListingEntity() {
    }

    public ListingEntity(Long id, CarEntity car, UserEntity user, BigDecimal listingPrice, ListingStatus listingStatus, LocalDateTime listingDate, String listingDescription) {
        this.id = id;
        this.car = car;
        this.user = user;
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

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

