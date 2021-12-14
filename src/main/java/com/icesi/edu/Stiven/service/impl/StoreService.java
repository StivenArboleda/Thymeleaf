package com.icesi.edu.Stiven.service.impl;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.DAO.StoreDAO;
import com.icesi.edu.Stiven.model.sales.Store;
import com.icesi.edu.Stiven.service.inter.IStoreService;

@Service
public class StoreService implements IStoreService{
	
	StoreDAO st;

	public StoreService(StoreDAO st) {
		this.st = st;
	}

	@Override
	public <S extends Store> S save(S store) {
		return (S) st.save(store);
	}

	@Override
	public <S extends Store> Iterable<S> saveAll(Iterable<S> a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store findbyId(Integer id) {
		return st.findById(id);
	}

	@Override
	public Iterable<Store> findAll() {
		return st.findAll();
	}

	@Override
	public void deletebyId(Integer id) {
		st.deleteById(id);
	}
	
	public void delete(Store s) {
		st.delete(s);
	}

	@Override
	public void editStore(Store o) {
		st.update(o);
	}

}
