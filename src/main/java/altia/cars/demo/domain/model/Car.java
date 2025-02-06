package altia.cars.demo.domain.model;

import java.math.BigDecimal;

public class Car {
    private Long id;
    private String carName;
    private String carModel;
    private String carDescription;
    private BigDecimal carPrice;
    private Boolean carAvailable;


    public Car(Long id, String carName, String carModel, String carDescription, BigDecimal carPrice, Boolean carAvailable) {
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

    public String getCarName() {
        return carName;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public Boolean isCarAvailable() {
        return carAvailable;
    }

    public Boolean getCarAvailable() {
        return carAvailable;
    }

    public void setCarAvailable(Boolean carAvailable) {
        this.carAvailable = carAvailable;
    }

}

