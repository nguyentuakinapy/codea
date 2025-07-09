package codea.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codea.dto.OrderRequest;
import codea.entity.Order;
import codea.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/order")
public class OrderRestController {
	@Autowired
	OrderService orderService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody OrderRequest req) {
		Order order = orderService.createOrder(req);
		return ResponseEntity.ok(order);
	}
}
