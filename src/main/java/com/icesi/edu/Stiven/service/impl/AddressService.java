package com.icesi.edu.Stiven.service.impl;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.repositories.AddressRepository;
import com.icesi.edu.Stiven.service.inter.IAddressService;

@Service
public class AddressService implements IAddressService{
	
	private AddressRepository as;
	
	public AddressService(AddressRepository as) {
		this.as = as;
	}
	
	public <S extends Address> S save(S address) {

		if(!(address.getCity().isEmpty()) && !(address.getPostalcode().isEmpty()) && !(address.getAddressline1().isEmpty())) {
			if(address.getStateprovince() != null) {
				as.save(address);
				return address;
				
			}else {
				throw new IllegalArgumentException("There is no state province");
			}
			
		}else if(address.getCity().isEmpty()) {
			throw new IllegalArgumentException("The city is empty");
		}else if(address.getPostalcode().isEmpty()) {
			throw new IllegalArgumentException("The postal code is empty");
		}else if(address.getAddressline1().isEmpty()) {
			throw new IllegalArgumentException("The address1 is empty");
		}
		
		return null;
	}
	
	public <S extends Address> Iterable<S> saveAll(Iterable<S> ads) {
		for (Address addre : ads) {
			save(addre);
		}
		return ads;
	}
	
	public Optional<Address> findById(Integer id) {
		return as.findById(id);
	}
	
	public boolean existsById(Integer id) {
		return as.existsById(id);
	}
	
	public Iterable<Address> findAllById(Iterable<Integer> ads) {
		return as.findAllById(ads);
	}
	@Override
	public Iterable<Address> findAll() {
		return as.findAll();
	}
	
	@Override
	public Address findbyId(Integer id) {
		return as.findById(id).get();
	}

	@Override
	public void deletebyId(Integer id) {
		as.deleteById(id);
	}
	
	public void editAddress(Integer addressid, String addressline1, String addressline2, String city, Timestamp modifieddate, String postalcode, 
			Integer rowguid, String spatiallocation) {
		
		if(!(city.isEmpty()) && !(postalcode.isEmpty()) && !(addressline1.isEmpty())) {
			
			Address ads = as.findById(addressid).get();
			
			ads.setAddressline1(addressline1);
			ads.setAddressline2(addressline2);
			ads.setCity(city);
			ads.setModifieddate(modifieddate);
			ads.setPostalcode(postalcode);
			ads.setRowguid(rowguid);
			ads.setSpatiallocation(spatiallocation);
			
			save(ads);
			
		}else {
			throw new IllegalArgumentException("One of the arguments is invalid");
		}
		
	}
}
