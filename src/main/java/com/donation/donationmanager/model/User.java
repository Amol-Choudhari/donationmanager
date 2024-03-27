package com.donation.donationmanager.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 65981149772133526L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "idtype")
	private String idtype;

	@Column(name = "idnumber")
	private String idNumber;
	
	private String email;

	@Column(name = "enabled", columnDefinition = "BIT", length = 1)
	private boolean enabled;

	@Column(name = "DISPLAY_NAME")
	private String displayName;

	@Column(name = "created_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	@Column(name = "modified_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedDate;

	private String password;

	private String provider;
	
	


}