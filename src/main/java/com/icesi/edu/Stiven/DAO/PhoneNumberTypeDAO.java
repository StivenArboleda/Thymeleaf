package com.icesi.edu.Stiven.DAO;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.icesi.edu.Stiven.model.person.Phonenumbertype;



@Repository
public class PhoneNumberTypeDAO implements Dao<Phonenumbertype> {
	
	@PersistenceContext
	private EntityManager entityManager;
	   
	public PhoneNumberTypeDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	
	}
	
	@Transactional
	@Override
	public Phonenumbertype findById(Integer id) {
		String jpql = "SELECT e FROM Phonenumbertype e WHERE e.phonenumbertypeid =: id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Phonenumbertype pt = null;
		try {
			pt = (Phonenumbertype) query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return pt;
	}

	@Override
	public List<Phonenumbertype> findAll() {
		Query query = entityManager.createQuery("SELECT e FROM Phonenumbertype e");
        return query.getResultList();
	}

	@Override
	public Phonenumbertype save(Phonenumbertype pt) {
		entityManager.persist(pt);
		return pt;
	}

	@Override
	public void update(Phonenumbertype pt) {
		entityManager.merge(pt);
	}

	@Override
	public void delete(Phonenumbertype pt) {
		entityManager.remove(pt);
	}
	

}
