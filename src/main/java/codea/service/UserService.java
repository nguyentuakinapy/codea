package codea.service;

import codea.entity.User;

public interface UserService {
	
	User findByUsername(String username);
	
}
