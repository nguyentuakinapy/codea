package codea.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codea.entity.Product;

@Service
public interface ProductService {
	List<Map<String, Object>> getProductsForHome();

	Page<Product> findProducts(Pageable pageable);
	
	Product createProduct(Product product);
	
	Product findById(Integer id);
}
