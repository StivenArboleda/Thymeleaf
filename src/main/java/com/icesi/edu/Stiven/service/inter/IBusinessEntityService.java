package com.icesi.edu.Stiven.service.inter;

import com.icesi.edu.Stiven.model.person.Businessentity;

public interface IBusinessEntityService {
	
	public Businessentity save(Businessentity be);
	public Iterable<Businessentity> findAll();
	
	
}
