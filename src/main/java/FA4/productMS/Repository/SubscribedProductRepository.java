package FA4.productMS.Repository;

import org.springframework.data.repository.CrudRepository;

import FA4.productMS.Entity.SubscribedProductEntity;
import FA4.productMS.Utility.CompoundKey;

public interface SubscribedProductRepository extends CrudRepository<SubscribedProductEntity, CompoundKey> {

}
