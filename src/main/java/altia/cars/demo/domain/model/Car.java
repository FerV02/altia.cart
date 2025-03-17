package altia.cars.demo.domain.model;

public class Car {
    private Long id;
    private String carBrand;
    private String carModel;
    private Integer carYear;
    private String carDescription;


    public Car(Long id, String carBrand, String carModel, Integer carYear, String carDescription) {
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

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
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

