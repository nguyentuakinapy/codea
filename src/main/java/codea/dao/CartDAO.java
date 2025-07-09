package codea.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Cart;

public interface CartDAO extends JpaRepository<Cart, Integer>{
	List<Cart> findByUserUserId(Integer userId);
	
	Optional<Cart> findByUserUserIdAndProductDetailSizeProductDetailSizeId(Integer userId, Integer productDetailSizeId);

	void deleteByUserUserIdAndProductDetailSizeProductDetailSizeId(Integer userId, Integer productDetailSizeId);
}
