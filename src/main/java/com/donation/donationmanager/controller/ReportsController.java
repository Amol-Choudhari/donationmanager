package com.donation.donationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donation.donationmanager.repository.DonationRepository;

@RestController
@RequestMapping("/reports")
public class ReportsController {
	
	@Autowired
	private DonationRepository donationRepository;
	
	@PostMapping("donationreport")
	

}
