package com.icesi.edu.Stiven.DAO;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.icesi.edu.Stiven.model.person.Addresstype;



@Repository
public class AddressTypeDAO implements Dao<Addresstype>{
	
	private final EntityManager entityManager;
	   
	public AddressTypeDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	
	}
	
	@Transactional
	@Override
	public Addresstype findById(Integer id) {
		String jpql = "SELECT e FROM Addresstype e WHERE e.adresstypeid=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Addresstype at = null;
		
		try {
			at = (Addresstype) query.getSingleResult();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return at;
	}
	
	@Transactional
	@Override
	public List<Addresstype> findAll() {
		Query query = entityManager.createQuery("SELECT e FROM Addresstype e");
        return query.getResultList();
	}
	@Transactional
	@Override
	public Addresstype save(Addresstype at) {
		entityManager.persist(at);
		return at;
	}
	@Transactional
	@Override
	public void update(Addresstype at) {
		entityManager.merge(at);
	}
	@Transactional
	@Override
	public void delete(Addresstype at) {
		entityManager.remove(at);
		
	}
	public void deleteId(Integer id) {
		Addresstype at = findById(id);
		entityManager.remove(at);
	}

}
