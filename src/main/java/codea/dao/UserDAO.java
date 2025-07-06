package codea.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import codea.entity.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);

//	@Query("SELECT u FROM BookingDetail bd JOIN bd.booking b JOIN b.user u "
//			+ "WHERE bd.bookingDetailId = :bookingDetailId")
//	User findUserByBookingDetailId(@Param("bookingDetailId") Integer bookingDetailId);
//	
//	@Query("SELECT o FROM User o WHERE username = ?1")
//	Optional<User> findByUsername(String username);
}
