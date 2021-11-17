package com.icesi.edu.Stiven.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Addresstype;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.repositories.AddressRepository;
import com.icesi.edu.Stiven.repositories.AddressTypeRepository;
import com.icesi.edu.Stiven.repositories.BusinessEntityAddressRepository;
import com.icesi.edu.Stiven.repositories.BusinessEntityRepository;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityAddressService;

@Service
public class BusinessEntityAddressService implements IBusinessEntityAddressService {
	
	BusinessEntityAddressRepository ber;
	AddressRepository addressR;
	AddressTypeRepository at;
	BusinessEntityRepository entity;
	
		
	public BusinessEntityAddressService(BusinessEntityAddressRepository ber, AddressRepository addressR,
			AddressTypeRepository at, BusinessEntityRepository entity) {
		this.ber = ber;
		this.addressR = addressR;
		this.at = at;
		this.entity = entity;
	}

	public Businessentityaddress save (Businessentityaddress bea, Integer address, Integer addressType, Integer business) {
		
		if(address != null) {
			if(addressType != null) {
				if(business != null) {
					
					Address a = addressR.findById(address).get();
					Addresstype atype = at.findById(addressType).get();
					Businessentity b = entity.findById(business).get();
					
					bea.setAddress(a);
					bea.setAddresstype(atype);
					bea.setBusinessentity(b);
					
					ber.save(bea);
					return bea;
				}else {
					throw new NullPointerException("The foreign key of the business entity does not exist");
				}
			}else {
				throw new NullPointerException("The foreign key of the address type does not exist");
			}
		}else {
			throw new NullPointerException("The foreign key of the address does not exist");
		}
		
	}
	
	public Optional<Businessentityaddress> findById(Integer id) {
		return ber.findById(id);
	}
	
	public Iterable<Businessentityaddress> findAll(){
		return ber.findAll();
	}
	
	@Override
	public void deletebyId(Integer id) {
		ber.deleteById(id);
	}

	public boolean existsById(Integer id) {
		return ber.existsById(id);
	}

	
	/*public void editBusinessentityaddress(Integer id, Integer address, Integer addressType, Integer business) {
		
		Businessentityaddress b = ber.findById(id).get();
		
		if(address != null) {
			if(addressType != null) {
				if(business != null) {
					
					Address a = addressR.findById(address).get();
					
					Businessentity be = entity.findById(business).get();
					Addresstype atype = at.findById(addressType).get();
					b.setAddress(a);
					b.setAddresstype(atype);
					b.setBusinessentity(be);
					
					ber.save(b);
				}else {
					throw new NullPointerException("The foreign key of the business entity does not exist");
				}
			}else {
				throw new NullPointerException("The foreign key of the address type does not exist");
			}
		}else {
			throw new NullPointerException("The foreign key of the address does not exist");
		}
	}*/


}
