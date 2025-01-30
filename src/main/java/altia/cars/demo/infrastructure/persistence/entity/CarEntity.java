package altia.cars.demo.infrastructure.persistence.entity;

import jakarta.persistence.*;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "Cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String carName;

    @Column(nullable = false)
    private String carModel;

    private String carDescription;

    @Column(nullable = false)
    private BigDecimal carPrice;

    @Column(nullable = false)
    private boolean carAvailable;

    public CarEntity () {}


    public CarEntity(Long id, String carName, String carModel, String carDescription, BigDecimal carPrice, boolean carAvailable) {
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

    public boolean isCarAvailable() {
        return carAvailable;
    }

    public void setCarAvailable(boolean carAvailable) {
        this.carAvailable = carAvailable;
    }

}
