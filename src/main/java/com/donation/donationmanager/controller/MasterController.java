package com.donation.donationmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("master")
public abstract class MasterController <T> {
	

	
	@GetMapping("fetchall")
	public abstract List<T> getAllRecords();
	
	//@PostMapping(value="adduser",consumes="application/json")
	@PostMapping("addnew")
	public abstract void addRecords(@RequestBody T record);
	
	@GetMapping("fetch/{id}")
	public abstract T getRecord(@PathVariable("id") Long id);
	
	@PutMapping("edit/{id}")
	public abstract void editRecord(@PathVariable("id") Long id,@RequestBody T record);
	
	@PutMapping("delete/{id}")
	public abstract void deleteRecord(@PathVariable Long id);
}
