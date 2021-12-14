package com.icesi.edu.Stiven.service.inter;

import com.icesi.edu.Stiven.model.sales.Store;

public interface IStoreService {
	public <S extends Store> S save(S store);
	
	public <S extends Store> Iterable<S> saveAll(Iterable<S> a);
	
	public Store findbyId(Integer id);
		
	public Iterable<Store> findAll();
	
	public void deletebyId(Integer id);
	
	public void editStore(Store o);
}