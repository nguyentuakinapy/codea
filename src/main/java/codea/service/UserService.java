package codea.service;

import java.util.List;
import java.util.Optional;

import codea.entity.User;

public interface UserService {
	List<User> findAllUser();
	
	Optional<User> findByEmail(String email);

	User create(User user);
	
	User update(User user);
}
