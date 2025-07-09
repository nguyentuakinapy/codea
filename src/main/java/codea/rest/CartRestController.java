package codea.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codea.dto.CartRequest;
import codea.dto.CartResponse;
import codea.dto.CartUpdate;
import codea.entity.Cart;
import codea.service.CartService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart")
public class CartRestController {
	@Autowired
	CartService cartService;
	
	@GetMapping
    public ResponseEntity<List<CartResponse>> getCartByUserId(@RequestParam("userId") Integer userId) {
        List<CartResponse> carts = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(carts);
    }
	
	@PostMapping
	public ResponseEntity<CartRequest> addToCart(@RequestBody CartRequest req) {
		CartRequest cart = cartService.addToCart(req);
        return ResponseEntity.ok(cart);
	}
	
	@PutMapping
	public ResponseEntity<?> updateQuantity(@RequestBody CartUpdate req) {
		cartService.updateQuantity(req.getCartId(), req.getQuantity());
		return ResponseEntity.ok("Số lượng đã được cập nhật");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeFromCart(@PathVariable("id") Integer cartId) {
		cartService.removeFromCart(cartId);
        return ResponseEntity.ok().build();
	}
}
