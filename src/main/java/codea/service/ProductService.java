package codea.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codea.dto.ProductCreateBody;
import codea.entity.Product;

@Service
public interface ProductService {
	List<Map<String, Object>> getProductsForHome();

	Product findById(Integer id);
	
	Page<Product> findProducts(Integer categoryId, Pageable pageable);
	
	Product createProduct(ProductCreateBody body);
	
	Product updateProduct(Integer id, ProductCreateBody product);
}
