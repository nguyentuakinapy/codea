package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Feedback;

public interface FeedbackDAO extends JpaRepository<Feedback, Integer>{

}
