package com.example.demo.service;

import java.util.List;

import org.springframework.context.annotation.Lazy;

import com.example.demo.model.User;


public interface UserService {

	List<User> getAllUsers();
	
	void deleteUser(String userId);
	
	void addUser(User user);
	
	User getUserById(String userId);
	
	User getUserByemailId(String emailId);
}
