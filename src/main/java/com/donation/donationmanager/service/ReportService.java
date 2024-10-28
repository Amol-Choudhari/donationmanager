package com.donation.donationmanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donation.donationmanager.model.Donation;
import com.donation.donationmanager.repository.DonationRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportService {

	@Autowired
    private DonationRepository donationRepository;

    public List<Donation> generateReport(LocalDate startDate, LocalDate endDate, Long userId) {
        return donationRepository.findByFilters(startDate, endDate, userId);
    }
    
    public void generatePdf(List<Donation> donations, HttpServletResponse response) {
        // Use PDF library to create and write the PDF to the response
    }
}

