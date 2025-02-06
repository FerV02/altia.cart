package altia.cars.demo.infrastructure.rest.request;

import java.math.BigDecimal;

public class CarRequest {

    private String carName;
    private String carModel;
    private String carDescription;
    private BigDecimal carPrice;
    private Boolean carAvailable;

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
