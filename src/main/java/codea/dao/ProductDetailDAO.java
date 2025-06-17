package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.ProductDetail;

public interface ProductDetailDAO extends JpaRepository<ProductDetail, Integer>{

}
