package com.donation.donationmanager.repositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	@PersistenceContext
	private EntityManager entityManager;
    
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
    	
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Donation> cq = cb.createQuery(Donation.class);
        Root<Donation> donation = cq.from(Donation.class);

        List<Predicate> predicates = new ArrayList<>();

        if (startDate != null && endDate != null) {
            predicates.add((Predicate) cb.between(donation.get("date"), startDate, endDate));
        }

        if (userId != null) {
            predicates.add((Predicate) cb.equal(donation.get("user").get("id"), userId));
        }

        cq.where((javax.persistence.criteria.Predicate[]) predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }


}
