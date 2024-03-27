package com.donation.donationmanager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.donation.donationmanager.model.User;
import com.donation.donationmanager.repository.UserDao;

@RestController
public class UserController {

	@Autowired private UserDao userDao;
	public List<User> getUser(){
		
		 return userDao.getUers().collect(Collectors.toList());
		 
	}
}
