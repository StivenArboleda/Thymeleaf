package com.icesi.edu.Stiven.service.inter;

import java.sql.Timestamp;
import java.util.Optional;
import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.model.person.BusinessentityaddressPK;

public interface IBusinessEntityAddressService {
	
	public Businessentityaddress save(Businessentityaddress be);
	
	public <S extends Businessentityaddress> Iterable<S> saveAll(Iterable<S> businessAddress);
	
	public Optional<Businessentityaddress> findById(Integer id);
	
	public Iterable<Businessentityaddress> findAll();

	public boolean existsById(Integer id);	
	
	public void deletebyId(Integer id);

	public void editBusinessentityaddress(Integer id);
	
}
