package altia.cars.demo.infrastructure.rest.response;

import altia.cars.demo.domain.model.Car;

public class CarResponse {

    private Long id;
    private String carBrand;
    private String carModel;
    private Integer carYear;
    private String carDescription;

    public CarResponse(Car car) {
        this.id = car.getId();
        this.carBrand = car.getCarBrand();
        this.carModel = car.getCarModel();
        this.carYear = car.getCarYear();
        this.carDescription = car.getCarDescription();
    }

    public CarResponse(Long id, String carBrand, String carModel, Integer carYear, String carDescription) {
        this.id = id;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carYear = carYear;
        this.carDescription = carDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcarBrand() {
        return carBrand;
    }

    public void setcarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }
}
