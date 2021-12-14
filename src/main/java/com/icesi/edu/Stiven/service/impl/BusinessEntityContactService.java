package com.icesi.edu.Stiven.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.DAO.BusinessEntityDAO;
import com.icesi.edu.Stiven.DAO.PersonDAO;
import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Addresstype;
import com.icesi.edu.Stiven.model.person.Businessentity;
import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.model.person.Businessentitycontact;
import com.icesi.edu.Stiven.model.person.Contacttype;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.repositories.AddressRepository;
import com.icesi.edu.Stiven.repositories.AddressTypeRepository;
import com.icesi.edu.Stiven.repositories.BusinessEntityAddressRepository;
import com.icesi.edu.Stiven.repositories.BusinessEntityContactRepository;
import com.icesi.edu.Stiven.repositories.BusinessEntityRepository;
import com.icesi.edu.Stiven.repositories.ContactTypeRepository;
import com.icesi.edu.Stiven.repositories.PersonRepository;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityAddressService;
import com.icesi.edu.Stiven.service.inter.IBusinessEntityContactService;

@Service
public class BusinessEntityContactService implements IBusinessEntityContactService {
	
	BusinessEntityContactRepository ber;
	ContactTypeRepository ct;
	BusinessEntityDAO entity;
	PersonDAO pr;
	

	public BusinessEntityContactService(BusinessEntityContactRepository ber, ContactTypeRepository ct,
			BusinessEntityDAO entity, PersonDAO pr) {
		this.ber = ber;
		this.ct = ct;
		this.entity = entity;
		this.pr = pr;
	}

	public Businessentitycontact save (Businessentitycontact bea, Integer business, Integer contact, Integer person) {
		
		if(business != null) {
			if(contact != null) {
				if(person != null) {
					
					Businessentity b = entity.findById(business);
					Contacttype c = ct.findById(contact).get();
					Person p = pr.findById(person);
					
					bea.setBusinessentity(b);
					bea.setContacttype(c);
					bea.setPerson(p);
					
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
	
	public Optional<Businessentitycontact> findById(Integer id) {
		return ber.findById(id);
	}
	
	public Iterable<Businessentitycontact> findAll(){
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
