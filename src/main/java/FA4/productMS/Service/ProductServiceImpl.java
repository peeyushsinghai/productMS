package FA4.productMS.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FA4.productMS.DTO.ProductDTO;
import FA4.productMS.Entity.ProductEntity;
import FA4.productMS.Exception.ProductException;
import FA4.productMS.Repository.ProductRepository;
import FA4.productMS.Validator.ProductValidator;


@Service(value = "productService")
@Transactional
public class ProductServiceImpl implements ProductService {
	
	
	@Autowired
	private ProductRepository productRepository;
	
	private static int productIdIndex;
	
	static {
		productIdIndex=101;
	}

	@Override
	public String addProduct(ProductDTO productDTO) throws ProductException {
		// TODO Auto-generated method stub
		ProductValidator.validateProduct(productDTO);
		
		ProductEntity product = productRepository.findByProductName(productDTO.getProductName());
		
		if(product != null)
			throw new ProductException("Sorry!! Product Already Presnet with This Details");
		
		product = new ProductEntity();
		
		String id = "P" + productIdIndex++;
		
		product.setProdId(id);
		product.setProductName(productDTO.getProductName());
		product.setPrice(productDTO.getPrice());
		product.setCategory(productDTO.getCategory());
		product.setDescription(productDTO.getDescription());
		product.setImage(productDTO.getImage());
		product.setSubCategory(productDTO.getSubCategory());
		product.setSellerId(productDTO.getSellerId());
		product.setProductRating(productDTO.getProductRating());
		product.setStock(productDTO.getStock());
		
		productRepository.save(product);
		
		return "Product added Successfully with productId: " + product.getProdId();
	}
	

	@Override
	public String deleteProduct(String id) throws ProductException {
		// TODO Auto-generated method stub
		ProductEntity product = productRepository.findByProdId(id);
		
		if(product == null)
			throw new ProductException("Sorry!! Cannot Delete Product");
		
		productRepository.delete(product);
		
		return "Product successfully deleted with Id: " + product.getProdId();

	}


	@Override
	public ProductDTO getProductByName(String name) throws ProductException {
		// TODO Auto-generated method stub
		ProductEntity product = productRepository.findByProductName(name);
		
		if(product == null)
			throw new ProductException("Sorry!! No Product Found With This Name");
		
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setProdId(product.getProdId());
		productDTO.setCategory(product.getCategory());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setPrice(product.getPrice());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductRating(product.getProductRating());
		productDTO.setSellerId(product.getSellerId());
		productDTO.setStock(product.getStock());
		productDTO.setSubCategory(product.getCategory());
		
		return productDTO;
	}

	@Override
	public List<ProductDTO> getProductByCategory(String category) throws ProductException {
		// TODO Auto-generated method stub
		List<ProductEntity> list = productRepository.findByCategory(category);
		
		if(list.isEmpty())
			throw new ProductException("Oops!! No Category Found");
		
		List<ProductDTO> prod_list = new ArrayList<>();
		
		for(ProductEntity product : list)
		{
			ProductDTO productDTO = new ProductDTO();
			
			productDTO.setProdId(product.getProdId());
			productDTO.setCategory(product.getCategory());
			productDTO.setDescription(product.getDescription());
			productDTO.setImage(product.getImage());
			productDTO.setPrice(product.getPrice());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductRating(product.getProductRating());
			productDTO.setSellerId(product.getSellerId());
			productDTO.setStock(product.getStock());
			productDTO.setSubCategory(product.getCategory());
			
			prod_list.add(productDTO);
		}
		
		return prod_list;
	}

	@Override
	public ProductDTO getProductById(String id) throws ProductException {
		// TODO Auto-generated method stub
		ProductEntity product = productRepository.findByProdId(id);
		
		if(product == null)
			throw new ProductException("Sorry!! No Product Found With This Id");
		
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setProdId(product.getProdId());
		productDTO.setCategory(product.getCategory());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setPrice(product.getPrice());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductRating(product.getProductRating());
		productDTO.setSellerId(product.getSellerId());
		productDTO.setStock(product.getStock());
		productDTO.setSubCategory(product.getCategory());
		
		return  productDTO ;
	}

	@Override
	public Boolean updateStockOfProd(String prodId, Integer quantity) throws ProductException {
		// TODO Auto-generated method stub
		Optional<ProductEntity> optProduct = productRepository.findById(prodId);
		ProductEntity product = optProduct.orElseThrow(()-> new ProductException("Product Does Not Exist"));
		if(product.getStock()>=quantity) {
			product.setStock(product.getStock()-quantity);
			return true;
		}
		return false;
	}

	@Override
	public List<ProductDTO> viewAllProducts() throws ProductException {
		// TODO Auto-generated method stub
		List<ProductEntity> list = productRepository.findAll();
		
		if(list.isEmpty())
			throw new ProductException("There are no products available.");
		
		List<ProductDTO> prod_list = new ArrayList<>();
		
		for(ProductEntity product : list) {
			
			ProductDTO prod = new ProductDTO();
			prod.setCategory(product.getCategory());
			prod.setDescription(product.getDescription());
			prod.setImage(product.getImage());
			prod.setPrice(product.getPrice());
			prod.setProdId(product.getProdId());
			prod.setProductName(product.getProductName());
			prod.setProductRating(product.getProductRating());
			prod.setSellerId(product.getSellerId());
			prod.setStock(product.getStock());
			prod.setSubCategory(product.getSubCategory());
			
			prod_list.add(prod);
		};
		
		return prod_list;
	}

}
