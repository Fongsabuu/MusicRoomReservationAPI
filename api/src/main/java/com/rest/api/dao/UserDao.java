package com.rest.api.dao;

import java.util.List;

import com.rest.api.model.User;

public interface UserDao {
	public int addUser(User user);
	
	public int updateUser(User user);
	
	public List<User> getUserById(int user_id);
	
	public List<User> getUserByEmail(User user);
	
	public List<User> getAllUser();
	
	public int deleteUser(int id);
	
	public int UpdateImgUser(int user_id, String img_name);
	
	public int updateUserStatus(int user_id, String user_status);
}
