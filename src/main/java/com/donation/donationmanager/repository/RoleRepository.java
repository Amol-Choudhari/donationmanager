package com.donation.donationmanager.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.donation.donationmanager.model.Role;

@Repository
@Transactional
public interface RoleRepository {
	
	Role findByName(String name);
}