package codea.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codea.dao.CartDAO;
import codea.dao.ProductDetailSizeDAO;
import codea.dao.UserDAO;
import codea.dto.CartRequest;
import codea.dto.CartResponse;
import codea.entity.Cart;
import codea.entity.ProductDetailSize;
import codea.entity.User;
import codea.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartDAO cartDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	ProductDetailSizeDAO detailSizeDAO;
	
	@Override
	public List<CartResponse> getCartByUserId(Integer userId) {
		return cartDAO.findByUserUserId(userId).stream().map(cart -> {
	        CartResponse res = new CartResponse();
	        res.setCartId(cart.getCartID());
	        res.setQuantity(cart.getQuantity());

	        var detailSize = cart.getProductDetailSize();
	        res.setProductDetailSizeId(detailSize.getProductDetailSizeId());
	        res.setSize(detailSize.getSize());
	        res.setPrice(detailSize.getPrice());
	        res.setRealPrice(detailSize.getRealPrice());

	        var detail = detailSize.getProductDetail();
	        res.setColorName(detail.getColor().getName());
	        res.setProductName(detail.getProduct().getName());
	        res.setImageUrl(detail.getGalleries().get(0).getImageUrl());

	        return res;
	    }).toList();
	}
	
	@Override
	public CartRequest addToCart(CartRequest req) {
		User user = userDAO.findById(req.getUserId()).get();
		ProductDetailSize productDetailSize = detailSizeDAO.findById(req.getProductDetailSizeId()).get();
		
		Optional<Cart> existingCartOpt = cartDAO.findByUserUserIdAndProductDetailSizeProductDetailSizeId(req.getUserId(),
				req.getProductDetailSizeId());
		
		if (existingCartOpt.isPresent()) {
	        Cart existingCart = existingCartOpt.get();
	        existingCart.setQuantity(existingCart.getQuantity() + req.getQuantity());
	        cartDAO.save(existingCart);
	    } else {
	        Cart cart = new Cart();
	        cart.setUser(user);
	        cart.setProductDetailSize(productDetailSize);
	        cart.setQuantity(req.getQuantity());
	        cartDAO.save(cart);
	    }
		
		return req;
	}
	
	@Override
	public void updateQuantity(Integer cartId, Integer quantity) {
	    Cart cart = cartDAO.findById(cartId).get();
	    cart.setQuantity(quantity);
        cartDAO.save(cart);
	}

	@Override
	public void removeFromCart(Integer cartId) {
		cartDAO.deleteById(cartId);
	}
}
