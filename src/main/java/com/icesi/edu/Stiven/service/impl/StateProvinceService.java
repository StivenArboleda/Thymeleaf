package com.icesi.edu.Stiven.service.impl;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.model.person.Stateprovince;
import com.icesi.edu.Stiven.repositories.StateProvinceRepository;
import com.icesi.edu.Stiven.service.inter.IStateProvinceService;

@Service
public class StateProvinceService implements IStateProvinceService{
	
	StateProvinceRepository stateR;
	
	public StateProvinceService(StateProvinceRepository stateR) {
		this.stateR = stateR;
	}
	
	@Override
	public Iterable<Stateprovince> findAll() {
		return stateR.findAll();
	}

	@Override
	public Stateprovince findbyId(Integer id) {
		return stateR.findById(id).get();
	}

	@Override
	public void deleteStateprovince(Stateprovince sp) {
		stateR.delete(sp);
		
	}

	@Override
	public Stateprovince saveCorrect(Stateprovince sp) {
		stateR.save(sp);
		return sp;
		
	}

}
