package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Integer>{

}
