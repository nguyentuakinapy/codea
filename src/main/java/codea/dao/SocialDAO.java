package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import codea.entity.Social;

public interface SocialDAO extends JpaRepository<Social, Integer> {

}
