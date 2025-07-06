package codea.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Social;

public interface SocialDAO extends JpaRepository<Social, Integer> {
	List<Social> findAllByIsPhoneTrueAndIsActiveTrue();
}
