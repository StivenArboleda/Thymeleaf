package com.icesi.edu.Stiven.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.icesi.edu.Stiven.model.sales.Store;

@Repository
@Scope("singleton")
public class StoreDAO implements Dao<Store>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public Store findById(Integer id) {
		String jpql = "SELECT e FROM Store e WHERE e.businessentityid = :id ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		
		Store a = null;
		try {
			a = (Store) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}

	@Transactional
	@Override
	public List<Store> findAll() {
		String jpql = "SELECT e FROM Store e";
        return entityManager.createQuery(jpql).getResultList();
	}

	@Transactional
	@Override
	public Store save(Store t) {
		entityManager.persist(t);
		return t;
	}

	@Transactional
	@Override
	public void update(Store t) {
		entityManager.merge(t);
	}

	@Transactional
	@Override
	public void delete(Store t) {
		entityManager.remove(t);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Store a = findById(id);
		entityManager.remove(a);
	}

}
