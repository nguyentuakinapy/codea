package codea.service;

import java.util.List;

import org.springframework.stereotype.Service;

import codea.dto.CartRequest;
import codea.dto.CartResponse;

@Service
public interface CartService {
	List<CartResponse> getCartByUserId(Integer userId);

	CartRequest addToCart(CartRequest req);
	
	void updateQuantity(Integer cartId, Integer quantity);
	
	void removeFromCart(Integer cartId);
}
