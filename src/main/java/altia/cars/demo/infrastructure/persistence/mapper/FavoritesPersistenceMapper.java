package altia.cars.demo.infrastructure.persistence.mapper;

import altia.cars.demo.domain.model.Favorites;
import altia.cars.demo.infrastructure.persistence.entity.FavoritesEntity;
import org.springframework.stereotype.Component;

@Component
public class FavoritesPersistenceMapper {
    public Favorites toDomain(FavoritesEntity favoritesEntity) {
        if (favoritesEntity == null) {
            return null;
        }
        return new Favorites(
                favoritesEntity.getId(),
                favoritesEntity.getUserId(),
                favoritesEntity.getListingId(),
                favoritesEntity.getFavoriteDate()
        );
    }

    public FavoritesEntity toEntity(Favorites favorites) {
        if (favorites == null) {
            return null;
        }
        return new FavoritesEntity(
                favorites.getId(),
                favorites.getUserId(),
                favorites.getListingId(),
                favorites.getFavoriteDate()
        );
    }
}