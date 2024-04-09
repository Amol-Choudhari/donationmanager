package com.donation.donationmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donation.donationmanager.model.User;
import com.donation.donationmanager.repository.UserRepository;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired private UserRepository userrepository;
	
	@GetMapping("getusers")
	public List<User> getAllUsers(){	
		 return userrepository.findAll();		 
	}
	
	@PostMapping(value="adduser",consumes="application/json")
	public void addUser(@RequestBody User user){		
		userrepository.save(user);		 
	}
	
	@GetMapping("getuser/{id}")
	public User getUser(@PathVariable Long id){	
		 return userrepository.findById(id);	 
	}
	
	@PutMapping("edituser/{id}")
	public void editUser(@PathVariable Long id){
		User existingUser = userrepository.findById(id);
        if (existingUser != null) {
		 userrepository.update(existingUser);	 
        }
	}
	
	@PutMapping("deleteuser/{id}")
	public void deleteUser(@PathVariable Long id){
		User existingUser = userrepository.findById(id);
        if (existingUser != null) {
		 userrepository.delete(existingUser);	 
        }
	}
}
