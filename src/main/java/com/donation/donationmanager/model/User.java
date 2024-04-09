package com.donation.donationmanager.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private int mobile;
	
	@Column(nullable=false)
	private int age;
	
	@Column(nullable=false)
	private String gender;
	
	@Column
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	/*@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date created;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modified;*/
	
	
	

}