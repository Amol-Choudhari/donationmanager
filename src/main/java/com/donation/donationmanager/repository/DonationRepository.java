package com.donation.donationmanager.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.donation.donationmanager.model.Donation;

@Repository
@Transactional
public interface DonationRepository {
    List<Donation> findAll();
    Donation findById(Long id);
    void save(Donation entity);
    void update(Donation entity);
    void delete(Donation entity);
    List<Donation> findByFilters(LocalDate startDate, LocalDate endDate, Long userId);
}