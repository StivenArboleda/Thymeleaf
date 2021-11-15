package com.icesi.edu.Stiven.service.inter;

import com.icesi.edu.Stiven.model.person.Stateprovince;

public interface IStateProvinceService {
	
	public Iterable<Stateprovince> findAll();
	
	public Stateprovince findbyId(Integer id);
	
	public void deleteStateprovince(Stateprovince sp);

	public Stateprovince saveCorrect(Stateprovince sp);
}
