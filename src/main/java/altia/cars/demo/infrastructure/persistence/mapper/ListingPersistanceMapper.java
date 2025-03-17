package altia.cars.demo.infrastructure.persistence.mapper;

import altia.cars.demo.domain.model.Listing;
import altia.cars.demo.infrastructure.persistence.entity.CarEntity;
import altia.cars.demo.infrastructure.persistence.entity.ListingEntity;
import altia.cars.demo.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ListingPersistanceMapper {
    public Listing toDomain(ListingEntity listingEntity) {
        if (listingEntity == null) {
            return null;
        }

        return new Listing(
                listingEntity.getId(),
                listingEntity.getCar().getId(),
                listingEntity.getUser().getId(),
                listingEntity.getListingPrice(),
                listingEntity.getListingStatus(),
                listingEntity.getListingDate(),
                listingEntity.getListingDescription()
        );
    }

    public ListingEntity toEntity(Listing listing) {
        if (listing == null) {
            return null;
        }

        ListingEntity listingEntity = new ListingEntity();
        listingEntity.setId(listing.getId());

        CarEntity car = new CarEntity();
        car.setId(listing.getCarId());
        listingEntity.setCar(car);

        UserEntity user = new UserEntity();
        user.setId(listing.getUserId());
        listingEntity.setUser(user);

        listingEntity.setListingPrice(listing.getListingPrice());
        listingEntity.setListingStatus(listing.getListingStatus());
        listingEntity.setListingDate(listing.getListingDate());
        listingEntity.setListingDescription(listing.getListingDescription());
        return listingEntity;
    }
}