package codea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import codea.dao.UserDAO;
import codea.entity.User;
import codea.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO userDao;

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
