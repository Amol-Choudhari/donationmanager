package com.donation.donationmanager.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donation.donationmanager.model.Role;
import com.donation.donationmanager.model.User;
import com.donation.donationmanager.repository.UserRepository;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired private UserRepository userrepository;
	
	@Autowired BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("getusers")
	public List<User> getAllUsers(){	
		 return userrepository.findAll();		 
	}
	
	//@PostMapping(value="adduser",consumes="application/json")
	/*@PostMapping("adduser")
	public void addUser(@RequestBody User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userrepository.save(user);		 
	}*/
	@PostMapping("adduser")
    public ResponseEntity<Boolean> addUser(@RequestBody User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userrepository.save(user);
            return ResponseEntity.ok(true); // User added successfully
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false); // Error occurred while adding user
        }
    }
	
	@GetMapping("getuser/{id}")
	public User getUser(@PathVariable("id") Long id){
		 return userrepository.findById(id); 
	}
	
	@PutMapping("edituser/{id}")
	public ResponseEntity<Boolean> editUser(@PathVariable("id") Long id,@RequestBody User user){
		try {
			User existingUser = userrepository.findById(id);
	        if (existingUser != null) {
	        	
	        	//set values from existing record to fields which will not be updated through request data
	        	user.setId(id);
	        	user.setCreated(existingUser.getCreated());
	        	user.setModified(LocalDate.now());
	        	user.setPassword(existingUser.getPassword());
	        	
	        	//rest fields will be updated from the request data
	        	userrepository.update(user);
	        	return ResponseEntity.ok(true); // User updated successfully
	        }else {
	        	return ResponseEntity.ok(false);//user not found
	        }
		} catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false); // Error occurred while adding user
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
