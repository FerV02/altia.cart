package altia.cars.demo.infrastructure.persistence.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_brand")
    private String carBrand;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "car_year")
    private Integer carYear;

    @Column(name = "car_description")
    private String carDescription;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListingEntity> listings;

    public CarEntity () {}


    public CarEntity(Long id, String carBrand, String carModel, Integer carYear, String carDescription) {
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
