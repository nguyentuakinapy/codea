package codea.service;

import java.util.List;

import org.springframework.stereotype.Service;

import codea.dto.FeedbackDTO;
import codea.entity.Feedback;

@Service
public interface FeedbackService {
	List<Feedback> getFeedbacksByProductId(Integer productId);
	Feedback createFeedback(FeedbackDTO dto);
}
