package altia.cars.demo.infrastructure.rest;

import altia.cars.demo.application.ports.in.CarServicePort;
import altia.cars.demo.domain.model.Car;
import altia.cars.demo.infrastructure.rest.request.CarRequest;
import altia.cars.demo.infrastructure.rest.response.CarResponse;
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

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CarResponse>> getCarsByName(@PathVariable String name) {
        List<Car> cars = servicePort.getCarsByName(name);
        List<CarResponse> carResponses = cars.stream()
                .map(car -> new CarResponse(
                        car.getId(),
                        car.getCarName(),
                        car.getCarModel(),
                        car.getCarDescription(),
                        car.getCarPrice(),
                        car.isCarAvailable()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(carResponses);
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<List<CarResponse>> getCarsByModel(@PathVariable String model) {
        List<Car> cars = servicePort.getCarsByModel(model);
        List<CarResponse> carResponses = cars.stream()
                .map(car -> new CarResponse(
                        car.getId(),
                        car.getCarName(),
                        car.getCarModel(),
                        car.getCarDescription(),
                        car.getCarPrice(),
                        car.isCarAvailable()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(carResponses);
    }

    @GetMapping("/expensive")
    public ResponseEntity<List<CarResponse>> getMostExpensiveCars() {
        List<Car> cars = servicePort.getMostExpensiveCars();
        List<CarResponse> carResponses = cars.stream()
                .map(car -> new CarResponse(
                        car.getId(),
                        car.getCarName(),
                        car.getCarModel(),
                        car.getCarDescription(),
                        car.getCarPrice(),
                        car.isCarAvailable()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(carResponses);
    }

    @DeleteMapping("/model/{model}")
    public ResponseEntity<String> deleteCarsByModel(@PathVariable String model) {
        boolean deleted = servicePort.deleteCarsByModel(model);
        if (deleted) {
            return ResponseEntity.ok("Modelo Eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo no encontrado");
        }
    }


    @GetMapping("/available/count")
    public ResponseEntity<Long> countAvailableCars() {
        long count = servicePort.countAvailableCars();
        return ResponseEntity.ok(count);
    }

    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {
        Car newCar = new Car(
                null,
                carRequest.getCarName(),
                carRequest.getCarModel(),
                carRequest.getCarDescription(),
                carRequest.getCarPrice(),
                carRequest.isCarAvailable()
        );

        Car createdCar = servicePort.addCar(newCar);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CarResponse(
                createdCar.getId(),
                createdCar.getCarName(),
                createdCar.getCarModel(),
                createdCar.getCarDescription(),
                createdCar.getCarPrice(),
                createdCar.isCarAvailable()
        ));
    }

    @GetMapping("/available")
    public ResponseEntity<List<CarResponse>> getAvailableCars() {
        List<Car> cars = servicePort.getAvailableCars();
        List<CarResponse> carResponses = cars.stream()
                .map(car -> new CarResponse(
                        car.getId(),
                        car.getCarName(),
                        car.getCarModel(),
                        car.getCarDescription(),
                        car.getCarPrice(),
                        car.isCarAvailable()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(carResponses);
    }
}

