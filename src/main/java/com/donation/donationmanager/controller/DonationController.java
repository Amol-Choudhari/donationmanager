package com.donation.donationmanager.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donation.donationmanager.model.Donation;
import com.donation.donationmanager.model.User;
import com.donation.donationmanager.repository.DonationRepository;
import com.donation.donationmanager.repository.UserRepository;
import com.donation.donationmanager.security.JwtHelper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("donation")
public class DonationController {

	@Autowired private DonationRepository donationrepository;
	@Autowired private HttpServletRequest request;
	@Autowired private JwtHelper jwtHelper;
	@Autowired private UserRepository userrepository;
	
	@GetMapping("getdonations")
	public List<Donation> getAllDonations(){	
		 return donationrepository.findAll();		 
	}
	
	@PostMapping("adddonation")
	public ResponseEntity<Map<String, Object>> addDonation(@RequestBody Donation donation) {
		Map<String, Object> response = new HashMap<>();
		try {
        	String requestHeader = request.getHeader("Authorization");
        	String token = requestHeader.substring(7);
        	String username = jwtHelper.getUsernameFromToken(token);
        	User userDetails = userrepository.findUserByUsername(username);
        	
        	donation.setBy_user(userDetails.getId());
        	donationrepository.save(donation);
        	
        	response.put("lastDonaId",donation.getId());     	
            return ResponseEntity.ok(response); // Donation added successfully then send last donation id in response
            
        } catch (Exception e) {
            e.printStackTrace();
            
            response.put("donationId",null);     	
            return ResponseEntity.ok(response); // Error occurred while adding Donation send null as response
        }
    }
	
	@GetMapping("getdonation/{id}")
	public Donation getDonation(@PathVariable("id") Long id){
		 return donationrepository.findById(id); 
	}
	
	/*@PutMapping("editdonation/{id}")
	public ResponseEntity<Boolean> editDonation(@PathVariable("id") Long id,@RequestBody Donation donation){
		try {
			Donation existingDonation = donationrepository.findById(id);
	        if (existingDonation != null) {
	        	
	        	//set values from existing record to fields which will not be updated through request data
	        	donation.setId(id);
	        	donation.setCreated(existingDonation.getCreated());
	        	donation.setTransaction_no(existingDonation.getTransaction_no());
	        	donation.setModified(LocalDate.now());
	        	
	        	//rest fields will be updated from the request data
	        	donationrepository.update(donation);
	        	return ResponseEntity.ok(true); // User updated successfully
	        }else {
	        	return ResponseEntity.ok(false);//user not found
	        }
		} catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false); // Error occurred while adding user
        }
	}*/
	
	@PutMapping("confirmdonation/{id}")
	public ResponseEntity<Boolean> confirmDonation(@PathVariable("id") Long id,@RequestBody Donation donation){
		try {
			Donation existingDonation = donationrepository.findById(id);
	        if (existingDonation != null) {
	        	
	        	//set values from existing record to fields which will not be updated through request data
	        	donation.setId(id);
	        	donation.setCreated(existingDonation.getCreated());
	        	donation.setTransaction_no(existingDonation.getTransaction_no());
	        	donation.setConfirmation("yes");
	        	donation.setModified(LocalDate.now());
	        	
	        	//rest fields will be updated from the request data
	        	donationrepository.update(donation);
	        	return ResponseEntity.ok(true); // User updated successfully
	        }else {
	        	return ResponseEntity.ok(false);//user not found
	        }
		} catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false); // Error occurred while adding user
        }
	}
	
	@PutMapping("deletedonation/{id}")
	public void deleteDonation(@PathVariable Long id){
		Donation existingDonation = donationrepository.findById(id);
        if (existingDonation != null) {
        	donationrepository.delete(existingDonation);	 
        }
	}
}
