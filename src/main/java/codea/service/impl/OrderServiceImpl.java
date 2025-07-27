package codea.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import codea.dao.OrderDAO;
import codea.dao.OrderDetailDAO;
import codea.dao.ProductDetailSizeDAO;
import codea.dao.UserDAO;
import codea.dao.VoucherDAO;
import codea.dto.OrderRequest;
import codea.entity.Order;
import codea.entity.OrderDetail;
import codea.entity.ProductDetailSize;
import codea.entity.User;
import codea.entity.Voucher;
import codea.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO orderDAO;
	@Autowired
	OrderDetailDAO detailDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	VoucherDAO voucherDAO;
	@Autowired
	ProductDetailSizeDAO detailSizeDAO;
	
	@Override
	public Order createOrder(OrderRequest req) {
		User user = userDAO.findById(req.getUserId()).get();
		
		Voucher voucher = null;
		if(req.getVoucherId() != null) voucher = voucherDAO.findById(req.getVoucherId()).get();
		
		Order order = new Order();
		order.setTotalPrice(req.getTotalPrice());
        order.setAddress(req.getAddress());
        order.setPhone(req.getPhone());
        order.setStatus(req.getStatus());
        order.setUser(user);
        order.setVoucher(voucher);
        
        Order savedOrder = orderDAO.save(order);
        
        List<OrderDetail> details = req.getOrderDetails().stream().map(detailRequest -> {
            ProductDetailSize pds = detailSizeDAO.findById(detailRequest.getProductDetailSizeId()).get();

            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);
            detail.setProductDetailSize(pds);
            detail.setQuantity(detailRequest.getQuantity());

            return detail;
        }).collect(Collectors.toList());
        
        detailDAO.saveAll(details);
        savedOrder.setOrderDetails(details);
        
		return savedOrder;
	}

	@Override
	public Page<Order> findOrder(Pageable pageable) {
		return orderDAO.findAll(pageable);
	}

	@Override
	public Order findById(Integer id) {
		return orderDAO.findById(id).get();
	}

	@Override
	public void updateStatus(Integer id, Integer status) {
		Order order = orderDAO.findById(id).get();
		order.setStatus(status);
		orderDAO.save(order);
	}
}
