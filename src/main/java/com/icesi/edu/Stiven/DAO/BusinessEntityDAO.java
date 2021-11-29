package com.icesi.edu.Stiven.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.icesi.edu.Stiven.model.person.Businessentity;


@Repository
@Transactional
public class BusinessEntityDAO implements Dao<Businessentity>{
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public Businessentity findById(Integer id) {
		String jpql = "SELECT e FROM Businessentity e WHERE e.businessentityid =: id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Businessentity be = null;
		try {
			be = (Businessentity) query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return be;
	}

	@Override
	@Transactional
	public List<Businessentity> findAll() {
		String jpql = "SELECT e FROM Businessentity e";
        return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	@Transactional
	public Businessentity save(Businessentity be) {
		entityManager.persist(be);
		return be;
	}
	
	@Override
	@Transactional
	public void update(Businessentity be) {
		entityManager.merge(be);	
	}

	@Transactional
	public void delete(Integer id) {
		Businessentity be = findById(id);
		entityManager.remove(be);
	}

	@Override
	public void delete(Businessentity t) {
		entityManager.remove(t);
	}


}
