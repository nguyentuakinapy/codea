package codea.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codea.dto.FeedbackDTO;
import codea.entity.Feedback;
import codea.service.FeedbackService;

@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin("*")
public class FeedbackRestController {
	@Autowired
	FeedbackService feedbackService;
	
	@GetMapping("/product/{id}")
	public ResponseEntity<List<Feedback>> getByProduct(@PathVariable Integer id) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByProductId(id);
        return ResponseEntity.ok(feedbacks);
    }
	
	@PostMapping
	public ResponseEntity<Feedback> create(@RequestBody FeedbackDTO dto) {
		Feedback feedback = feedbackService.createFeedback(dto);
		return ResponseEntity.ok(feedback);
	}
}
