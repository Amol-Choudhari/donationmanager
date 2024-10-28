package com.donation.donationmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donation.donationmanager.dto.ReportRequest;
import com.donation.donationmanager.model.Donation;
import com.donation.donationmanager.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reports")
public class ReportsController {
	
	@Autowired
	private ReportService reportService;
	
	@PostMapping("donationreport")
	public void generateReport(@RequestBody ReportRequest reportRequest, HttpServletResponse response) {
		
		List<Donation> donations = reportService.generateReport(
	        reportRequest.getStartDate(),
	        reportRequest.getEndDate(),
	        reportRequest.getUserId()
	    );
	    response.setContentType("application/pdf");
	    response.addHeader("Content-Disposition", "attachment; filename=donation_report.pdf");
	    reportService.generatePdf(donations, response);
		
	}
	

}
