package codea.service;

import java.util.List;

import codea.entity.User;

public interface UserService {
	
	User findByUsername(String username);
	
	List<User> findAllUser();
	
	User create(User user);
	
	User update(User user);
}
