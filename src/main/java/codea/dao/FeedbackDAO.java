package codea.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Feedback;

public interface FeedbackDAO extends JpaRepository<Feedback, Integer>{
	List<Feedback> findByProduct_ProductId(Integer productId);
}
