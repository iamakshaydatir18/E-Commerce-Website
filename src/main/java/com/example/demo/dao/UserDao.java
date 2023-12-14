package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.User;


public interface UserDao {

	List<User> getAllUsers();

	void deleteUser(String userId);
	
	void addUser(User user);
	
	User getUserById(String userId);
	
	User getUserByemailId(String emailId);
}
