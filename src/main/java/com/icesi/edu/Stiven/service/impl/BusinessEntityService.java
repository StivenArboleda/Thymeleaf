package com.icesi.edu.Stiven.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.DAO.BusinessEntityDAO;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.repositories.BusinessEntityRepository;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityService;
	
@Service
public class BusinessEntityService implements IBusinessEntityService{
	
	private BusinessEntityDAO businessEntityR;
	
	public BusinessEntityService(BusinessEntityDAO businessEntityR) {
		this.businessEntityR = businessEntityR;
	}
	
	public Businessentity save(Businessentity businessentity) {
		businessentity = new Businessentity(); 
		businessentity.setModifieddate(Timestamp.from(Instant.now()));
		if(businessentity.getModifieddate() != null) {
			businessentity = businessEntityR.save(businessentity);
			return businessentity;
		}else {
			throw new NullPointerException("Modification date does not exist");
		}
	}
	
	public Businessentity saveForAddress(Businessentity businessentity) {
		//businessentity = new Businessentity(); 
		businessentity.setModifieddate(Timestamp.from(Instant.now()));
		if(businessentity.getModifieddate() != null) {
			businessentity = businessEntityR.save(businessentity);
			return businessentity;
		}else {
			throw new NullPointerException("Modification date does not exist");
		}
	}
	
	public Businessentity findById(Integer id) {
		return businessEntityR.findById(id);
	}
	
	@Override
	public Iterable<Businessentity> findAll() {
		return businessEntityR.findAll();
	}
	
	
	public void editBusinessEntity(Integer businessentityid, Timestamp modifieddate, Integer rowguid) {
		
		if(modifieddate != null) {
			Businessentity be = findById(businessentityid);
			
			be.setModifieddate(modifieddate);
			be.setRowguid(rowguid);
			
			save(be);
		}
	}
}
