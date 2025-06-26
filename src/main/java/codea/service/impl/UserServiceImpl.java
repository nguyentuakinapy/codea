package codea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codea.dao.UserDAO;
import codea.entity.User;
import codea.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAO userDao;

	@Override
	public User findByEmailUser(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public List<User> findAllUser() {
		return userDao.findAll();
	}

	@Override
	public User create(User user) {
		return userDao.save(user);
	}

	@Override
	public User update(User user) {
		return userDao.save(user);
	}

}
