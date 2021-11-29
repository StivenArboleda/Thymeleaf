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
import com.icesi.edu.Stiven.model.person.Businessentityaddress;



@Repository
@Transactional
public class BusinessEntityAddressDAO implements Dao<Businessentityaddress> {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Businessentityaddress findById(Integer id) {
		String jpql = "SELECT e FROM Businessentityaddress e WHERE e.Businessentityaddressid=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Businessentityaddress be = null;
		try {
			be = (Businessentityaddress) query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return be;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Businessentityaddress> findAll() {
		Query query = entityManager.createQuery("SELECT e FROM Businessentityaddress e");
        return query.getResultList();
	}

	@Override
	public Businessentityaddress save(Businessentityaddress be) {
		entityManager.persist(be);
		return be;
	}

	@Override
	public void update(Businessentityaddress t) {
		entityManager.merge(t);
		
	}

	@Override
	public void delete(Businessentityaddress t) {
		entityManager.remove(t);
	}
	
	
	public void delete(Integer id) {
		Businessentityaddress be = findById(id);
		entityManager.remove(be);
		
	}

}
