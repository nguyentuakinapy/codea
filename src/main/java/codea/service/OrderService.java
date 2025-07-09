package codea.service;

import org.springframework.stereotype.Service;

import codea.dto.OrderRequest;
import codea.entity.Order;

@Service
public interface OrderService {
	Order createOrder(OrderRequest req);
	
}
