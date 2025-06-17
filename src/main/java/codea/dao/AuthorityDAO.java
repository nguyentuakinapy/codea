package codea.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import codea.entity.Authority;
import codea.entity.User;
import jakarta.transaction.Transactional;

public interface AuthorityDAO extends JpaRepository<Authority, Integer>{
	@Modifying
    @Transactional
	@Query("DELETE FROM Authority a WHERE a.user = :user")
    void deleteByUser(@Param("user") User user);
}
