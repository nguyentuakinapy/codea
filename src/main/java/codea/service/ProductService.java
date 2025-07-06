package codea.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import codea.entity.Product;

@Service
public interface ProductService {

	Page<Product> findAllUserSoft(Integer page, Integer size);

	Product findbyId(Integer id);
	
	List<Map<String, Object>> getProductsForHome();
}
