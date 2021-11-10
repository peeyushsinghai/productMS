package FA4.productMS.Repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import FA4.productMS.Entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, String> {
	
	public ProductEntity findByProdId(String id);
	
	public ProductEntity findByProductName(String name);
	
	public List<ProductEntity> findByCategory(String category);
	
	public List<ProductEntity> findAll();

}
