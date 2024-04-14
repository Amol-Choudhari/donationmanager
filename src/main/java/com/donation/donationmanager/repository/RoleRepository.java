package com.donation.donationmanager.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.donation.donationmanager.model.Role;
import com.donation.donationmanager.model.User;

@Repository
@Transactional
public interface RoleRepository {
    /*List<User> findAll();
    User findById(Long id);
    void save(User entity);
    void update(User entity);
    void delete(User entity);*/
	
	Role findByName(String name);
}