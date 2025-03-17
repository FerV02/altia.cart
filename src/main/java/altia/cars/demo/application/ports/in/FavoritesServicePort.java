package altia.cars.demo.application.ports.in;

import altia.cars.demo.domain.model.Favorites;
import java.util.List;
import java.util.Optional;

public interface FavoritesServicePort {
    Favorites addFavorite(Favorites favorite);
    List<Favorites> getFavoritesByUser(Long userId);
    List<Favorites> getFavoritesByListing(Long listingId);
    Optional<Favorites> getFavoriteById(Long id);
    void removeFavorite(Long id, Long userId);
}