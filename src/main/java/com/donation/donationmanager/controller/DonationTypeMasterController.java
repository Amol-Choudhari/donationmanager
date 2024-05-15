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

import com.donation.donationmanager.model.DonationType;
import com.donation.donationmanager.repositoryImpl.DonationTypeRepositoryImpl;
@RestController
@RequestMapping("master/donationtype")
public class DonationTypeMasterController extends MasterController<DonationType> {

	@Autowired DonationTypeRepositoryImpl donationTypeRepositoryImpl;
	
	@GetMapping("fetchall")
	public List<DonationType> getAllRecords(){
		 return donationTypeRepositoryImpl.findAll();		 
	}
	
	//@PostMapping(value="adduser",consumes="application/json")
	@PostMapping("addnew")
	public void addRecords(@RequestBody DonationType record){		
		donationTypeRepositoryImpl.save(record);		 
	}
	
	@GetMapping("fetch/{id}")
	public DonationType getRecord(@PathVariable("id") Long id){
		 return donationTypeRepositoryImpl.findById(id); 
	}
	
	@PutMapping("edit/{id}")
	public void editRecord(@PathVariable("id") Long id,@RequestBody DonationType record){
		DonationType existingRecord = donationTypeRepositoryImpl.findById(id);
        if (existingRecord != null) {
        	
        	//set values from existing record to fields which will not be updated through request data
        	record.setId(id);
        	record.setCreated(existingRecord.getCreated());
        	record.setModified(LocalDate.now());
        	
        	//rest fields will be updated from the request data
        	donationTypeRepositoryImpl.update(record);
        }
	}
	
	@PutMapping("delete/{id}")
	public void deleteRecord(@PathVariable Long id){
		DonationType existingRecord = donationTypeRepositoryImpl.findById(id);
        if (existingRecord != null) {
        	donationTypeRepositoryImpl.delete(existingRecord);	 
        }
	}

}
