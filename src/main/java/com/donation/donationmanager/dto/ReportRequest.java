package com.donation.donationmanager.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRequest {
	
	private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;

}
