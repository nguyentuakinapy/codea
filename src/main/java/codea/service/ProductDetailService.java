package codea.service;

import org.springframework.stereotype.Service;

import codea.dto.ProductDetailCreateRequest;
import codea.dto.ProductDetailUpdateRequest;
import codea.entity.ProductDetail;

@Service
public interface ProductDetailService {
	ProductDetail create(ProductDetailCreateRequest body);
	
	ProductDetail update(ProductDetailUpdateRequest body);
	
	void delete(Integer id);
}
