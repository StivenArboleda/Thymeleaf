package com.icesi.edu.Stiven.DAO;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Businessentity;


@Repository
@Scope("singleton")
public class AddressDAO implements Dao<Address> {
	
	@PersistenceContext
	private EntityManager entityManager;
	


	@Transactional
	@Override
	public List<Address> findAll() {
		String jpql = "SELECT e FROM Address e";
        return entityManager.createQuery(jpql).getResultList();
	}
	
	@Transactional
	@Override
	public Address findById(Integer id) {
		String jpql = "SELECT e FROM Address e WHERE e.businessentityid = :id ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		
		Address a = null;
		try {
			a = (Address) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
	
	@Transactional
	public List<Address> findByDate(String date) {
		String jpql = "SELECT e FROM Address e WHERE modifieddate e.modifieddate  = :date"; 
		Query query = entityManager.createQuery(jpql);
		query.setParameter("date", date);
        return query.getResultList();
	}
	
	@Transactional
	@Override
	public Address save(Address a) {
		entityManager.persist(a);
		return a;
	}
	@Transactional
	@Override
	public void update(Address a) {
		entityManager.merge(a);
	}
	@Transactional
	public void delete(Integer id) {
		Address a = findById(id);
		entityManager.remove(a);
		
	}

	@Override
	public void delete(Address a) {
		entityManager.remove(a);
	}

	
}
