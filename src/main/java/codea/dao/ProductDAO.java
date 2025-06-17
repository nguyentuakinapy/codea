package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

}
