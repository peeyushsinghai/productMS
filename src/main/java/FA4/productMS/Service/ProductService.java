package FA4.productMS.Service;


import java.util.List;

import FA4.productMS.DTO.ProductDTO;
import FA4.productMS.Exception.ProductException;

public interface ProductService {
	
	public String addProduct(ProductDTO productDTO) throws ProductException;
	
	public String deleteProduct(String id) throws ProductException;
	
	public ProductDTO getProductByName(String name) throws ProductException;
	
	public List<ProductDTO> getProductByCategory(String category) throws ProductException;
	
	public ProductDTO getProductById(String id) throws ProductException;

	public Boolean updateStockOfProd(String prodId, Integer quantity) throws ProductException;
	
	public List<ProductDTO> viewAllProducts() throws ProductException;

}
