package altia.cars.demo.infrastructure.rest;

import altia.cars.demo.application.ports.in.CarServicePort;
import altia.cars.demo.domain.model.Car;
import altia.cars.demo.infrastructure.rest.request.CarRequest;
import altia.cars.demo.infrastructure.rest.response.CarResponse;
import jakarta.persistence.PersistenceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarRestAdapter {

    private final CarServicePort servicePort;

    public CarRestAdapter(CarServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<CarResponse>> getCarsByBrand(@PathVariable String brand) {
        try {
            List<Car> cars = servicePort.getCarsByBrand(brand);
            List<CarResponse> carResponses = cars.stream()
                    .map(CarResponse::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(carResponses);
        } catch (PersistenceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<List<CarResponse>> getCarsByModel(@PathVariable String model) {
        List<Car> cars = servicePort.getCarsByModel(model);
        List<CarResponse> carResponses = cars.stream()
                .map(CarResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carResponses);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<CarResponse>> getCarsByYear (@PathVariable Integer year) {
        List<Car> cars = servicePort.getCarsByYear(year);
        List<CarResponse> carResponses = cars.stream()
                .map(CarResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carResponses);
    }


    @DeleteMapping("/model/{model}")
    public ResponseEntity<String> deleteCarsByModel(@PathVariable String model) {
        Boolean deleted = servicePort.deleteCarsByModel(model);
        if (deleted) {
            return ResponseEntity.ok("Modelo Eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {
        Car newCar = new Car(
                null,
                carRequest.getCarBrand(),
                carRequest.getCarModel(),
                carRequest.getCarYear(),
                carRequest.getCarDescription()
        );

        Car createdCar = servicePort.addCar(newCar);
        CarResponse carResponse = new CarResponse(createdCar);
        return ResponseEntity.status(HttpStatus.CREATED).body(carResponse);
    }


    @GetMapping("/")
    public ResponseEntity<Page<CarResponse>> findByCriteria(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Car> cars = servicePort.findByCriteria(brand, model, year, pageable);

        Page<CarResponse> carResponses = cars.map(CarResponse::new);

        return ResponseEntity.ok(carResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        return servicePort.getCarById(id)
                .map(car -> ResponseEntity.ok(new CarResponse(car)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> findAllCars() {
        List<Car> cars = servicePort.findAllCars();
        List<CarResponse> responses = cars.stream()
                .map(CarResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable Long id, @RequestBody CarRequest carRequest) {
        Car car = new Car(
                id,
                carRequest.getCarBrand(),
                carRequest.getCarModel(),
                carRequest.getCarYear(),
                carRequest.getCarDescription()
        );

        return servicePort.updateCar(id, car)
                .map(updatedCar -> ResponseEntity.ok(new CarResponse(updatedCar)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCarsByIds(@RequestBody List<Long> ids) {
        boolean deleted = servicePort.deleteCarsByIds(ids);
        if (deleted) {
            return ResponseEntity.ok("Coches eliminados correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar los coches");
        }
    }


}

