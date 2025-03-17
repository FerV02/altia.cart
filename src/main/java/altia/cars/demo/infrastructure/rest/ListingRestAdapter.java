package altia.cars.demo.infrastructure.rest;

import altia.cars.demo.application.ports.in.ListingServicePort;
import altia.cars.demo.domain.model.Listing;
import altia.cars.demo.domain.model.ListingStatus;
import altia.cars.demo.domain.model.UserRole;
import altia.cars.demo.infrastructure.rest.request.ListingRequest;
import altia.cars.demo.infrastructure.rest.response.ListingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/listings")
public class ListingRestAdapter {

    private final ListingServicePort listingService;

    public ListingRestAdapter(ListingServicePort listingService) {
        this.listingService = listingService;
    }

    @PostMapping
    public ListingResponse createListing(@RequestBody ListingRequest request) {
        LocalDateTime listingDate = request.getListingDate() != null ? request.getListingDate() : LocalDateTime.now();
        Listing listing = new Listing(
                null,
                request.getCarId(),
                request.getUserId(),
                request.getListingPrice(),
                request.getListingStatus(),
                listingDate,
                request.getListingDescription()
        );
        Listing createdListing = listingService.createListing(listing);
        return new ListingResponse(createdListing);
    }

    @GetMapping("/{id}")
    public ListingResponse getListingById(@PathVariable Long id) {
        Optional<Listing> listing = listingService.getListingById(id);
        return listing.map(ListingResponse::new).orElseThrow(() -> new RuntimeException("Listing not found"));
    }

    @GetMapping
    public List<ListingResponse> getAllListings() {
        return listingService.getAllListings().stream()
                .map(ListingResponse::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public String deleteListing(@PathVariable Long id, @RequestParam Long userId) {
        Boolean deleted = listingService.deleteListing(id, userId);
        if (deleted) {
            return "Listing deleted successfully.";
        } else {
            throw new RuntimeException("Failed to delete listing.");
        }
    }

    @GetMapping("/status/{status}")
    public List<ListingResponse> getListingsByStatus(@PathVariable ListingStatus status) {
        return listingService.getListingsByStatus(status).stream()
                .map(ListingResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public List<ListingResponse> getListingsByUserId(@PathVariable Long userId) {
        return listingService.getListingsByUserId(userId).stream()
                .map(ListingResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/car/{carId}")
    public List<ListingResponse> getListingsByCarId(@PathVariable Long carId) {
        return listingService.getListingsByCarId(carId).stream()
                .map(ListingResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/price")
    public List<ListingResponse> getListingsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        return listingService.getListingsByPriceRange(minPrice, maxPrice).stream()
                .map(ListingResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/date")
    public List<ListingResponse> getListingsByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return listingService.getListingsByDateRange(startDate, endDate).stream()
                .map(ListingResponse::new)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{id}/status")
    public String updateListingStatus(@PathVariable Long id, @RequestParam ListingStatus status, @RequestParam Long userId) {
        listingService.updateListingStatus(id, status, userId);
        return "Listing status updated successfully.";
    }

    @GetMapping("/brand/{brand}/model/{model}")
    public List<ListingResponse> getListingsByCarBrandAndModel(
            @PathVariable String brand,
            @PathVariable String model) {
        return listingService.getListingsByCarBrandAndModel(brand, model).stream()
                .map(ListingResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/role/{role}")
    public List<ListingResponse> getListingsByUserRole(@PathVariable UserRole role) {
        return listingService.getListingsByUserRole(role).stream()
                .map(ListingResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public Page<ListingResponse> searchListings(
            @RequestParam(required = false) ListingStatus status,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return listingService.findByCriteria(status, userId, carId, minPrice, maxPrice, startDate, endDate, pageable)
                .map(ListingResponse::new);
    }
}