package com.icesi.edu.Stiven.service.inter;

import java.sql.Timestamp;
import java.util.Optional;

import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.model.person.BusinessentityaddressPK;

public interface IBusinessEntityAddressService {
	
	public <S extends Businessentityaddress> S save(S be);
	
	public <S extends Businessentityaddress> Iterable<S> saveAll(Iterable<S> businessAddress);
	
	public Optional<Businessentityaddress> findById(BusinessentityaddressPK id);

	public boolean existsById(BusinessentityaddressPK id);
	
	public Iterable<Businessentityaddress> findAllById(Iterable<BusinessentityaddressPK> businessAddress);
	
	public void editBusinessentityaddress(BusinessentityaddressPK id, Timestamp modifieddate, Integer rowguid);
	
}
