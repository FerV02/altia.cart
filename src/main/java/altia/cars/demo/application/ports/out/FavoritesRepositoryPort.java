package altia.cars.demo.application.ports.out;

import altia.cars.demo.domain.model.Favorites;
import java.util.List;
import java.util.Optional;

public interface FavoritesRepositoryPort {
    Favorites save(Favorites favorite);
    Optional<Favorites> findById(Long id);
    List<Favorites> findByUserId(Long userId);
    List<Favorites> findByListingId(Long listingId);
    void deleteById(Long id);
}