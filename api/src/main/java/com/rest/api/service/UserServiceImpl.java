package com.rest.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.dao.UserDao;
import com.rest.api.model.User;

@Service  
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public List<User> getUserByEmail(User user) {
		return userDao.getUserByEmail(user);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public int deleteUser(int id) {
		return userDao.deleteUser(id);
	}

	@Override
	public int UpdateImgUser(int user_id, String img_name) {
		return userDao.UpdateImgUser(user_id, img_name);
	}

	@Override
	public List<User> getUserById(int user_id) {
		return userDao.getUserById(user_id);
	}
}
