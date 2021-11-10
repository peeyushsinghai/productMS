package FA4.productMS.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import FA4.productMS.DTO.ProductDTO;
import FA4.productMS.Service.ProductService;

@RestController
public class productcontroller {
	
	@Autowired
	private ProductService productService;
	@Value("${order.uri}")
	String orderUri;
	
	@Value("${user.uri}")
	String userUri;
	// To add Product
	@PostMapping(value = "product/addProduct")
	public ResponseEntity<String> addProduct(@RequestBody ProductDTO prod){
		
		try {
			String add_Success = productService.addProduct(prod);
			return new ResponseEntity<>(add_Success,HttpStatus.ACCEPTED);
		}
		catch(Exception exception)
		{
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.UNAUTHORIZED);
		}
		
	}
	// To get Product by Name
	@GetMapping(value = "product/getByName/{name}")
	public ResponseEntity<ProductDTO> getByProductName(@PathVariable String name)
	{
		
		try {
			ProductDTO productDTO = productService.getProductByName(name);
			return new ResponseEntity<>(productDTO,HttpStatus.OK);
		}
		catch(Exception exception)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage(),exception);
		}
	}
	// TO get product by Id
	@GetMapping(value = "/product/getById/{id}")
	public ResponseEntity<ProductDTO> getByProductId(@PathVariable String id)
	{
		try {
			ProductDTO productDTO = productService.getProductById(id);
			return new ResponseEntity<>(productDTO,HttpStatus.OK);
		}
		catch(Exception exception)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage(),exception);
		}
	}
	// To get Product by Category
	@GetMapping(value = "/product/getByCategory/{name}")
	public ResponseEntity<List<ProductDTO>> getByProductCategory(@PathVariable String name)
	{
		
		try {
			List<ProductDTO> productDTO = productService.getProductByCategory(name);
			return new ResponseEntity<List<ProductDTO>>(productDTO,HttpStatus.OK);
		}
		catch(Exception exception)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage(),exception);
		}
	}
	// To delete Product from DB
	@DeleteMapping(value = "/product/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable String id){
		
		try {
			String msg = productService.deleteProduct(id);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch(Exception exception)
		{
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	// To update stock of a Product
	@GetMapping(value = "/product/updateStock/{prodId}/{quantity}")
	public ResponseEntity<Boolean> updateStock(@PathVariable String prodId, @PathVariable Integer quantity){		
		try {
			Boolean status = productService.updateStockOfProd(prodId,quantity);
			return new ResponseEntity<>(status,HttpStatus.ACCEPTED);
		}
		catch(Exception exception)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage(),exception);
		}		
	}
	// To view all Products
	@GetMapping(value = "/product/viewAllProducts")
	public ResponseEntity<List<ProductDTO>> viewAllProducts()
	{
		try {
			List<ProductDTO> list = productService.viewAllProducts();
			return new ResponseEntity<>(list,HttpStatus.OK);
		}
		catch(Exception exception)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage(),exception);
		}
	}


}
