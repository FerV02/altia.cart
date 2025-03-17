package altia.cars.demo.application.service;

import altia.cars.demo.application.ports.in.FavoritesServicePort;
import altia.cars.demo.application.ports.out.FavoritesRepositoryPort;
import altia.cars.demo.domain.model.Favorites;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritesService implements FavoritesServicePort {
    private final FavoritesRepositoryPort repository;

    public FavoritesService(FavoritesRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Favorites addFavorite(Favorites favorite) {
        List<Favorites> existingFavorites = repository.findByUserId(favorite.getUserId());
        boolean alreadyExists = existingFavorites.stream()
                .anyMatch(f -> f.getListingId().equals(favorite.getListingId()));
        if (alreadyExists) {
            throw new IllegalArgumentException("El listing ya está en favoritos.");
        }
        return repository.save(favorite);
    }

    @Override
    public List<Favorites> getFavoritesByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Favorites> getFavoritesByListing(Long listingId) {
        return repository.findByListingId(listingId);
    }

    @Override
    public Optional<Favorites> getFavoriteById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void removeFavorite(Long id, Long userId) {
        Optional<Favorites> favorite = repository.findById(id);
        if (favorite.isPresent() && favorite.get().getUserId().equals(userId)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No tienes permiso para eliminar este favorito.");
        }
    }
}