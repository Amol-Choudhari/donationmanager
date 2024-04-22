package com.donation.donationmanager.repositoryImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.donation.donationmanager.model.Donation;
import com.donation.donationmanager.repository.DonationRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DonationRepositoryImpl implements DonationRepository {
	
	@Autowired
    private SessionFactory sessionFactory;
    
    public DonationRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<Donation> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Donation", Donation.class).list();
        }
    }

    @Override
    public Donation findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Donation.class, id);
        }
    }

    @Override
    public void save(Donation entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Donation entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Donation entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }


}
