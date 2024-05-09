package com.donation.donationmanager.repositoryImpl;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.donation.donationmanager.model.Role;
import com.donation.donationmanager.repository.MasterRepository;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@Component
public class RoleMasterRepositoryImpl  implements MasterRepository<Role> {
	

		@Autowired
	    private SessionFactory sessionFactory;
	    
	    public RoleMasterRepositoryImpl(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	    
	 
	    @Override
	    public List<Role> findAll() {
	        try (Session session = sessionFactory.openSession()) {
	            return session.createQuery("FROM Role", Role.class).list();
	        }
	    }

	    @Override
	    public Role findById(Long id) {
	        try (Session session = sessionFactory.openSession()) {
	            return session.get(Role.class, id);
	        }
	    }

	    @Override
	    public void save(Role entity) {
	        try (Session session = sessionFactory.openSession()) {
	            session.beginTransaction();
	            session.save(entity);
	            session.getTransaction().commit();
	        }
	    }

	    @Override
	    public void update(Role entity) {
	        try (Session session = sessionFactory.openSession()) {
	            session.beginTransaction();
	            session.update(entity);
	            session.getTransaction().commit();
	        }
	    }

	    @Override
	    public void delete(Role entity) {
	        try (Session session = sessionFactory.openSession()) {
	            session.beginTransaction();
	            session.delete(entity);
	            session.getTransaction().commit();
	        }
	    }
	}


