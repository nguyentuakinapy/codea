package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Cart;

public interface CartDAO extends JpaRepository<Cart, Integer>{

}
