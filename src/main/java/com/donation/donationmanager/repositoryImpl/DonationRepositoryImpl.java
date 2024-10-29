package com.donation.donationmanager.repositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.donation.donationmanager.model.Donation;
import com.donation.donationmanager.repository.DonationRepository;

import lombok.NoArgsConstructor;

@Component
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
    
    @Override
    public List<Donation> findByFilters(LocalDate startDate, LocalDate endDate, Long userId) {
        StringBuilder hql = new StringBuilder("FROM Donation d WHERE 1=1");

        if (startDate != null) {
            hql.append(" AND d.created >= :startDate");
        }
        if (endDate != null) {
            hql.append(" AND d.created <= :endDate");
        }
        if (userId != null) {
            hql.append(" AND d.by_user = :userId");
        }

        Query<Donation> query = sessionFactory.getCurrentSession().createQuery(hql.toString(), Donation.class);

        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }
        if (userId != null) {
            query.setParameter("userId", userId);
        }

        return query.list();
    }


}
