package mapogo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mapogo.entity.Feedback;

public interface FeedbackDAO extends JpaRepository<Feedback, Integer>{

}
