package com.donation.donationmanager.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donation.donationmanager.model.User;
import com.donation.donationmanager.repository.UserDao;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired private UserDao userDao;
	
	@GetMapping("getusers")
	public List<User> getUsers(){
		
		 return userDao.getUsers();
		 
	}
	
	@GetMapping("adduser")
	public void addUser(){
		
		User user = new User();
        user.setId(1L); // Assuming ID is auto-generated, you can set it accordingly
        user.setIdtype("Passport");
        user.setIdNumber("AB123456");
        user.setEmail("john.doe@example.com");
        user.setEnabled(true);
        user.setDisplayName("John Doe");
        user.setCreatedDate(new Date());
        user.setModifiedDate(new Date());
        user.setPassword("password123");
        user.setProvider("local");
        
		 userDao.addUser(user);
		 
	}
}
