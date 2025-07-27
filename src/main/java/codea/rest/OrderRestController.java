package codea.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codea.dto.OrderRequest;
import codea.dto.PagedResponse;
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
	
	@PostMapping("/page")
	public PagedResponse<Order> getOrdersByPage(@RequestParam(defaultValue = "1") Integer pageIndex,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		int serverPageIndex = Math.max(pageIndex - 1, 0);
		Page<Order> orderPage = orderService.findOrder(PageRequest.of(serverPageIndex, pageSize));
		return new PagedResponse<>(orderPage.getTotalElements(), orderPage.getTotalPages(), pageIndex,
				orderPage.getSize(), orderPage.getContent());
	}
	
	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable("id") Integer id) {
		return orderService.findById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestParam(defaultValue = "1") Integer status) {
		orderService.updateStatus(id, status);
		return ResponseEntity.ok(Map.of("message", "Cập nhật thành công!"));
	}
}
