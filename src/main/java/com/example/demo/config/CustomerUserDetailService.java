package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;

@Service
public class CustomerUserDetailService implements UserDetailsService {

	@Autowired
	UserServiceImpl userfind;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userfind.getUserByemailId(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User Not found Exception!!!!!!!!");
		}
		
		return new CustomUserDetails(user);
	}
	
	

}
