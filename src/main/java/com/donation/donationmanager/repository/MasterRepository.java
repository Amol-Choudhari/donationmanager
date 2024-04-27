package com.donation.donationmanager.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MasterRepository<T> {
	void save(T entity);
    T findById(Long id);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
}
