package com.donation.donationmanager.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class User {
	
	@Transient
	@Autowired BCryptPasswordEncoder passwordEncoder;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String mobile;
	
	@Column(nullable=false)
	private int age;
	
	@Column(nullable=false)
	private String gender;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column
	private LocalDate created;

	@Column
	private LocalDate modified;
	
	private String jwtToken;
	
	@PrePersist
    public void prePersist() {
		created = LocalDate.now();
		modified = LocalDate.now();
    }
	
	// Hash the password before setting it
    public void setPassword(String password) {
        this.password = passwordEncoder.encode(password);
    }

    // Validate password by comparing the hashed password
    public boolean isPasswordValid(String rawPassword) {
        return passwordEncoder.matches(rawPassword, this.password);
    }
    
    
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();
	

}