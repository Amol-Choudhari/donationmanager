package com.donation.donationmanager.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donation.donationmanager.model.Role;
import com.donation.donationmanager.repositoryImpl.RoleMasterRepositoryImpl;
@RestController
@RequestMapping("master/roles")
public class RoleMasterController extends MasterController<Role> {

	@Autowired RoleMasterRepositoryImpl roleMasterRepositoryImpl;
	
	@GetMapping("fetchall")
	public List<Role> getAllRecords(){
		 return roleMasterRepositoryImpl.findAll();		 
	}
	
	//@PostMapping(value="adduser",consumes="application/json")
	@PostMapping("addnew")
	public void addRecords(@RequestBody Role record){		
		roleMasterRepositoryImpl.save(record);		 
	}
	
	@GetMapping("fetch/{id}")
	public Role getRecord(@PathVariable("id") Long id){
		 return roleMasterRepositoryImpl.findById(id); 
	}
	
	@PutMapping("edit/{id}")
	public void editRecord(@PathVariable("id") Long id,@RequestBody Role record){
		Role existingRecord = roleMasterRepositoryImpl.findById(id);
        if (existingRecord != null) {
        	
        	//set values from existing record to fields which will not be updated through request data
        	record.setId(id);
        	record.setCreated(existingRecord.getCreated());
        	record.setModified(LocalDate.now());
        	
        	//rest fields will be updated from the request data
        	roleMasterRepositoryImpl.update(record);
        }
	}
	
	@PutMapping("delete/{id}")
	public void deleteRecord(@PathVariable Long id){
		Role existingRecord = roleMasterRepositoryImpl.findById(id);
        if (existingRecord != null) {
        	roleMasterRepositoryImpl.delete(existingRecord);	 
        }
	}
}
