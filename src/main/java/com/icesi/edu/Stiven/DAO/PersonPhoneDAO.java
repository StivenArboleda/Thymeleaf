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
import com.icesi.edu.Stiven.model.person.Personphone;


@Repository
@Transactional
public class PersonPhoneDAO implements Dao<Personphone> {
	
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public Personphone findById(Integer id) {
		String jpql = "SELECT e FROM Personphone e WHERE e.id =:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Personphone pp = null;
		try {
			pp = (Personphone) query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return pp;
	}
	
	
	@Transactional
	public List<Personphone> getByPref(String pref) {
		String jpql = "SELECT e FROM Personphone e WHERE phone LIKE " + "\'"+pref+"%\'";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("pref", pref);
        return query.getResultList();
	}
	
	@Transactional
	public List<Personphone> getByType(String type) {
		String jpql = "SELECT e FROM Personphone e";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("type", type);
		return query.getResultList();
	}

	@Override
	@Transactional
	public List<Personphone> findAll() {
		Query query = entityManager.createQuery("SELECT e FROM Personphone e");
        return query.getResultList();
	}

	@Override
	@Transactional
	public Personphone save(Personphone pp) {
		entityManager.persist(pp);
		return pp;
	}

	@Override
	@Transactional
	public void update(Personphone pp) {
		entityManager.merge(pp);	
	}

	@Override
	@Transactional
	public void delete(Personphone pp) {
		entityManager.remove(pp);
		
	}	
	
	@Transactional
	public void delete(Integer id) {
		Personphone pp = findById(id);
		entityManager.remove(pp);
	}

}
