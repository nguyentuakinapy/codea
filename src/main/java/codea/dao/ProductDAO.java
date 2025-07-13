package codea.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import codea.entity.Product;
import codea.entity.ProductDetail;


public interface ProductDAO extends JpaRepository<Product, Integer>{
	List<Product> findByStatus(Integer status);
	List<Product> findTop8ByCategoryCategoryIdAndStatusOrderByDateDesc(Integer categoryId, Integer status);
	Page<Product> findByCategoryCategoryId(Integer categoryId, Pageable pageable);
}
