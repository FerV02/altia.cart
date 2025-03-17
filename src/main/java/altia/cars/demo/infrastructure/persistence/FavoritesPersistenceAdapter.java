package altia.cars.demo.infrastructure.persistence;

import altia.cars.demo.application.ports.out.FavoritesRepositoryPort;
import altia.cars.demo.domain.model.Favorites;
import altia.cars.demo.infrastructure.persistence.entity.FavoritesEntity;
import altia.cars.demo.infrastructure.persistence.mapper.FavoritesPersistenceMapper;
import altia.cars.demo.infrastructure.persistence.repository.FavoritesRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FavoritesPersistenceAdapter implements FavoritesRepositoryPort {
    private final FavoritesRepository repository;
    private final FavoritesPersistenceMapper mapper;

    public FavoritesPersistenceAdapter(FavoritesRepository repository, FavoritesPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Favorites save(Favorites favorite) {
        FavoritesEntity entity = mapper.toEntity(favorite);
        FavoritesEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Favorites> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Favorites> findByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Favorites> findByListingId(Long listingId) {
        return repository.findByListingId(listingId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}