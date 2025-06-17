package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer>{

}
