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

import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Person;


@Repository
@Transactional
public class PersonDAO implements Dao<Person>{
	
	
	@PersistenceContext
	private EntityManager entityManager;
	   
	
	//BUSACRSE POR LA ENTIDAD DE NEGOCIO
	@Override
	@Transactional
	public Person findById(Integer id) {
		String jpql = "SELECT t FROM Person t WHERE t.businessentityid =: id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Person p = null;
		try {
			p = (Person) query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return p;
	}
	

	@Transactional
	public List<Person> findByTitle(String title) {
		String jpql = "SELECT e FROM Address e WHERE modifieddate e.title  = :title"; 
		Query query = entityManager.createQuery(jpql);
		query.setParameter("title", title);
        return query.getResultList();
	}

	@Override
	@Transactional
	public List<Person> findAll() {
		String jpql = "SELECT e FROM Person e";
        return entityManager.createQuery(jpql).getResultList();
	}
	
	@Transactional
	public List<Person> findByDateInterval(String date1, String date2) {
		Query query = entityManager.createQuery("SELECT e FROM Person e WHERE modifieddate BETWEEN " + "\'"+date1+"\'" 
				+ " AND " + "\'"+date2+"\'" + " AND counter >= 1 ORDER BY lastname");
        return query.getResultList();
	}
	
	@Override
	@Transactional
	public Person save(Person p) {
		entityManager.persist(p);
		return p;
	}

	@Override
	@Transactional
	public void update(Person p) {
		entityManager.merge(p);
	}

	@Override

	public void delete(Person p) {
		entityManager.remove(p);		
	}
	@Transactional
	public void delete(Integer id) {
		Person be = findById(id);
		entityManager.remove(be);
		
	}


}
 