package com.icesi.edu.Stiven.service.impl;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.DAO.StateProvinceDAO;
import com.icesi.edu.Stiven.model.person.Stateprovince;
import com.icesi.edu.Stiven.service.inter.IStateProvinceService;

@Service
public class StateProvinceService implements IStateProvinceService{
	
	StateProvinceDAO stateR;
	
	public StateProvinceService(StateProvinceDAO stateR) {
		this.stateR = stateR;
	}
	
	@Override
	public Iterable<Stateprovince> findAll() {
		return stateR.findAll();
	}

	@Override
	public Stateprovince findbyId(Integer id) {
		return stateR.findById(id);
	}

	@Override
	public void deleteStateprovince(Stateprovince sp) {
		stateR.delete(sp);
		
	}
	@Override
	public void delete(Integer id) {
		stateR.delete(id);
	}

	@Override
	public Stateprovince saveCorrect(Stateprovince sp) {
		stateR.save(sp);
		return sp;
	}

}
