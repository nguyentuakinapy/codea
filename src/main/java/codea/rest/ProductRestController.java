package codea.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codea.dto.PagedResponse;
import codea.dto.ProductCreateBody;
import codea.dto.ProductPagingFilter;
import codea.entity.Product;
import codea.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductRestController {
	@Autowired
	ProductService productService;
	
	@PostMapping("/page")
	public PagedResponse<Product> getProductsByPage(@RequestBody ProductPagingFilter filter) {
		int serverPageIndex = Math.max(filter.getPageIndex() - 1, 0);
		Page<Product> productPage = productService.findProducts(filter.getCategoryId(),
				PageRequest.of(serverPageIndex, filter.getPageSize()));
	    return new PagedResponse<>(productPage.getTotalElements(), productPage.getTotalPages(), filter.getPageIndex(),
	    		productPage.getSize(), productPage.getContent());
	}
	
	@PostMapping
	public Product createProduct(@RequestBody ProductCreateBody body) {
		return productService.createProduct(body);
	}
	
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable("id") Integer id, @RequestBody ProductCreateBody product) {
	    return productService.updateProduct(id, product);
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}
	
}
