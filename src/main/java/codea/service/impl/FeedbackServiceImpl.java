package codea.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codea.dao.FeedbackDAO;
import codea.dao.ProductDAO;
import codea.dao.UserDAO;
import codea.dto.FeedbackDTO;
import codea.entity.Feedback;
import codea.entity.Product;
import codea.entity.User;
import codea.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	UserDAO userDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	FeedbackDAO feedbackDAO;
	
	@Override
	public List<Feedback> getFeedbacksByProductId(Integer productId) {
		return feedbackDAO.findByProduct_ProductId(productId);
	}
	
	@Override
	public Feedback createFeedback(FeedbackDTO dto) {
		Product product = productDAO.findById(dto.getProductId()).get();
		
		User user = userDAO.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("Chưa đăng nhập"));
		
		Feedback feedback = new Feedback();
		feedback.setProduct(product);
		feedback.setUser(user);
		feedback.setStar(dto.getStar());
		feedback.setComment(dto.getComment());
		feedback.setDate(LocalDate.now());
		return feedbackDAO.save(feedback);
	}
}
