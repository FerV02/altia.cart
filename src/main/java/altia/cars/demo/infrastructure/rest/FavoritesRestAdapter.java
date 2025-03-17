package altia.cars.demo.infrastructure.rest;

import altia.cars.demo.application.ports.in.FavoritesServicePort;
import altia.cars.demo.domain.model.Favorites;
import altia.cars.demo.infrastructure.rest.request.FavoritesRequest;
import altia.cars.demo.infrastructure.rest.response.FavoritesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favorites")
public class FavoritesRestAdapter {
    private final FavoritesServicePort favoriteService;

    public FavoritesRestAdapter(FavoritesServicePort favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FavoritesResponse addFavorite(@RequestBody FavoritesRequest request) {
        LocalDateTime favoriteDate = request.getFavoriteDate() != null ? request.getFavoriteDate() : LocalDateTime.now();
        Favorites favorite = new Favorites(
                null,
                request.getUserId(),
                request.getListingId(),
                favoriteDate
       );
        Favorites createdFavorite = favoriteService.addFavorite(favorite);
        return new FavoritesResponse(
                createdFavorite.getId(),
                createdFavorite.getUserId(),
                createdFavorite.getListingId(),
                createdFavorite.getFavoriteDate()
        );
    }

    @GetMapping("/user/{userId}")
    public List<FavoritesResponse> getFavoritesByUser(@PathVariable Long userId) {
        return favoriteService.getFavoritesByUser(userId).stream()
                .map(FavoritesResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/listing/{listingId}")
    public List<FavoritesResponse> getFavoritesByListing(@PathVariable Long listingId) {
        return favoriteService.getFavoritesByListing(listingId).stream()
                .map(FavoritesResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FavoritesResponse getFavoriteById(@PathVariable Long id) {
        Favorites favorite = favoriteService.getFavoriteById(id)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));
        return new FavoritesResponse(
                favorite.getId(),
                favorite.getUserId(),
                favorite.getListingId(),
                favorite.getFavoriteDate()
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFavorite(@PathVariable Long id, @RequestParam Long userId) {
        favoriteService.removeFavorite(id, userId);
    }
}