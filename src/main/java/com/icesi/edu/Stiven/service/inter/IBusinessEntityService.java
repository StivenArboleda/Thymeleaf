package com.icesi.edu.Stiven.service.inter;

import com.icesi.edu.Stiven.model.person.Businessentity;

public interface IBusinessEntityService {
	
	public <S extends Businessentity> S save(S be);
	
}
