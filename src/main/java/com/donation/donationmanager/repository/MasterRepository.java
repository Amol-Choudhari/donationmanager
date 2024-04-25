package com.donation.donationmanager.repository;

import java.util.List;

public interface MasterRepository<T> {
	void save(T entity);
    T findById(Long id);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
}
