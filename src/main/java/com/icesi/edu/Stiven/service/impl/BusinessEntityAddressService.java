package com.icesi.edu.Stiven.service.impl;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.model.person.BusinessentityaddressPK;
import com.icesi.edu.Stiven.repositories.BusinessEntityAddressRepository;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityAddressService;

@Service
public class BusinessEntityAddressService implements IBusinessEntityAddressService {
	
	BusinessEntityAddressRepository ber;
	
	public BusinessEntityAddressService(BusinessEntityAddressRepository ber) {
		this.ber = ber;
	}
	
	public <S extends Businessentityaddress> S save(S businessAddress) {
		BusinessentityaddressPK pk = businessAddress.getId();
		if(pk != null) {
			if(pk.getBusinessentityid() != null ) {
				if(pk.getAddresstypeid() != null) {
					if(pk.getAddressid() != null) {
						
						ber.save(businessAddress);
						return businessAddress;
						
					}else {
						throw new NullPointerException("The foreign key of the address does not exist");
					}
				}else {
					throw new NullPointerException("The foreign key of the address type does not exist");
				}
			}else {
				throw new NullPointerException("The foreign key of the business entity does not exist");
			}		
		}else {
			throw new NullPointerException("The foreign key of the business entity address does not exist");
		}
		
	}
	
	public <S extends Businessentityaddress> Iterable<S> saveAll(Iterable<S> businessAddress) {
		for (Businessentityaddress doc : businessAddress) {
			save(doc);
		}
		return businessAddress;
	}
	
	public Optional<Businessentityaddress> findById(BusinessentityaddressPK id) {
		return ber.findById(id);
	}

	public boolean existsById(BusinessentityaddressPK id) {
		return ber.existsById(id);
	}
	
	public Iterable<Businessentityaddress> findAllById(Iterable<BusinessentityaddressPK> businessAddress) {
		return ber.findAllById(businessAddress);
	}
	
	public void editBusinessentityaddress(BusinessentityaddressPK id, Timestamp modifieddate, Integer rowguid) {
		
		Businessentityaddress b = ber.findById(id).get();
		
		b.setModifieddate(modifieddate);
		b.setRowguid(rowguid);
		
		save(b);
	}


}
