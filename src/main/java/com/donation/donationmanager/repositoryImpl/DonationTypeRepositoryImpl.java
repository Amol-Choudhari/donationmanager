package com.donation.donationmanager.repositoryImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.donation.donationmanager.model.Donation;
import com.donation.donationmanager.model.DonationType;
import com.donation.donationmanager.repository.MasterRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DonationTypeRepositoryImpl implements MasterRepository<DonationType>{

	@Autowired
    private SessionFactory sessionFactory;
    
    public DonationTypeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<DonationType> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM DonationType", DonationType.class).list();
        }
    }

    @Override
    public DonationType findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(DonationType.class, id);
        }
    }

    @Override
    public void save(DonationType entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(DonationType entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(DonationType entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}
