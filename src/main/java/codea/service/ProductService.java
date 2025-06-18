package codea.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import codea.entity.Brand;
import codea.entity.Product;

@Service
public interface ProductService {

	Page<Product> findAllUserSoft(Integer page, Integer size);

	Product findbyId(Integer id);

	List<Product> findByBrand(Brand brand);
}
