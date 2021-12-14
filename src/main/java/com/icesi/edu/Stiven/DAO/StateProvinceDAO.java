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
import com.icesi.edu.Stiven.model.person.Stateprovince;

@Repository
@Transactional
public class StateProvinceDAO implements Dao<Stateprovince>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public Stateprovince findById(Integer id) {
		String jpql = "SELECT t Stateprovince FROM Stateprovince t WHERE t.stateprovinceid =: id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Stateprovince sp = null;
		try {
			sp = (Stateprovince) query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return sp;
	}
	@Transactional
	@Override
	public List<Stateprovince> findAll() {
		String jpql = "SELECT e FROM Stateprovince e";
        return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	@Transactional
	public Stateprovince save(Stateprovince sp) {
		entityManager.persist(sp);
		return sp;
	}

	@Override
	@Transactional
	public void update(Stateprovince sp) {
		entityManager.merge(sp);		
	}

	@Override
	public void delete(Stateprovince t) {
		entityManager.remove(t);		
	}
	@Transactional
	public void delete(Integer id) {
		Stateprovince t = findById(id);
		entityManager.remove(t);;
		
	}

	
	

}
