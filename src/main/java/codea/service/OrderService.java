package codea.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codea.dto.OrderRequest;
import codea.entity.Order;

@Service
public interface OrderService {
	Order createOrder(OrderRequest req);
	Page<Order> findOrder(Pageable pageable);
	Order findById(Integer id);
	void updateStatus(Integer id, Integer status);
}
