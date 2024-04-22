package com.donation.donationmanager.repositoryImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.donation.donationmanager.model.User;
import com.donation.donationmanager.repository.UserRepository;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
    private SessionFactory sessionFactory;
    
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public User findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    @Override
    public void save(User entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(User entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
    
    @Override
    public User findUserByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
        	Query<User> query = session.createQuery("FROM User where username=:username", User.class);
        	query.setParameter("username", username);
            List<User> users = query.getResultList();
            return users.isEmpty() ? null : users.get(0);
        }
    }

}
