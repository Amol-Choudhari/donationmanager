package com.donation.donationmanager.model;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DonationType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String type;
	
	@Column
	private LocalDate created;

	@Column
	private LocalDate modified;
	
	@PrePersist
    public void prePersist() {
		created = LocalDate.now();
		modified = LocalDate.now();
    }

	

}