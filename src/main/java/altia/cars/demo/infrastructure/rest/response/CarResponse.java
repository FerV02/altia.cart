package altia.cars.demo.infrastructure.rest.response;

import java.math.BigDecimal;

public class CarResponse {

    private Long id;
    private String carName;
    private String carModel;
    private String carDescription;
    private BigDecimal carPrice;
    private Boolean carAvailable;

    public CarResponse() {}

    public CarResponse(Long id, String carName, String carModel, String carDescription, BigDecimal carPrice, Boolean carAvailable) {
        this.id = id;
        this.carName = carName;
        this.carModel = carModel;
        this.carDescription = carDescription;
        this.carPrice = carPrice;
        this.carAvailable = carAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(BigDecimal carPrice) {
        this.carPrice = carPrice;
    }

    public Boolean isCarAvailable() {
        return carAvailable;
    }

    public void setCarAvailable(Boolean carAvailable) {
        this.carAvailable = carAvailable;
    }
}
