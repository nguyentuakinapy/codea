package codea.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Brand;
import codea.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	
	List<Product> findByBrand(Brand brand);
}
