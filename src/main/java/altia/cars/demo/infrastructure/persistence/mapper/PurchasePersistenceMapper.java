package altia.cars.demo.infrastructure.persistence.mapper;

import altia.cars.demo.domain.model.Purchase;
import altia.cars.demo.infrastructure.persistence.entity.PurchaseEntity;
import org.springframework.stereotype.Component;

@Component
public class PurchasePersistenceMapper {
    public Purchase toDomain(PurchaseEntity purchaseEntity) {
        if (purchaseEntity == null) {
            return null;
        }
        return new Purchase(
                purchaseEntity.getId(),
                purchaseEntity.getUserId(),
                purchaseEntity.getListingId(),
                purchaseEntity.getPurchaseDate()
        );
    }

    public PurchaseEntity toEntity(Purchase purchase) {
        if (purchase == null) {
            return null;
        }
        return new PurchaseEntity(
                purchase.getId(),
                purchase.getUserId(),
                purchase.getListingId(),
                purchase.getPurchaseDate()
        );
    }
}