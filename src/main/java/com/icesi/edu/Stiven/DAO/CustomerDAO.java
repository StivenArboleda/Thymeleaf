package com.icesi.edu.Stiven.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icesi.edu.Stiven.model.sales.Customer;
import com.icesi.edu.Stiven.model.sales.Store;

@Repository
@Scope("singleton")
public class CustomerDAO implements Dao<Customer>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public Customer findById(Integer id) {
		String jpql = "SELECT e FROM Customer e WHERE e.customerid = :id ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		
		Customer a = null;
		try {
			a = (Customer) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}

	@Transactional
	@Override
	public List<Customer> findAll() {
		String jpql = "SELECT e FROM Customer e";
        return entityManager.createQuery(jpql).getResultList();
	}

	@Transactional
	@Override
	public Customer save(Customer t) {
		entityManager.persist(t);
		return t;
	}

	@Transactional
	@Override
	public void update(Customer t) {
		entityManager.merge(t);
	}

	@Transactional
	@Override
	public void delete(Customer t) {
		entityManager.remove(t);
	}
	
	@Transactional
	public void deletebyId(Integer id) {
		Customer a = findById(id);
		entityManager.remove(a);
	}

}
