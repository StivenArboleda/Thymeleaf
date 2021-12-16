package com.icesi.edu.Stiven.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.DAO.AddressDAO;
import com.icesi.edu.Stiven.DAO.StateProvinceDAO;
import com.icesi.edu.Stiven.model.person.Address;
import com.icesi.edu.Stiven.model.person.Stateprovince;
import com.icesi.edu.Stiven.service.inter.IAddressService;


@Service
public class AddressService implements IAddressService{
	
	private AddressDAO as;
	private StateProvinceDAO sps;
	
	public AddressService(AddressDAO as, StateProvinceDAO sps) {
		this.as = as;
		this.sps = sps;
	}
	
	public <S extends Address> S save(S address) {

		if(!(address.getCity().isEmpty()) && !(address.getPostalcode().isEmpty()) && !(address.getAddressline1().isEmpty())) {
			if(address.getStateprovince() != null) {
				address.getStateprovince().setModifieddate(LocalDate.now());
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
	
	public Address saveCorrect(Address address) {

		if(!(address.getCity().isEmpty()) && !(address.getPostalcode().isEmpty()) && !(address.getAddressline1().isEmpty())) {
			if(address.getStateprovince() != null) {
				address.getStateprovince().setModifieddate(LocalDate.now());
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
	
	public Address findById(Integer id) {
		return as.findById(id);
	}
	
	/*public boolean existsById(Integer id) {
		return as.existsById(id);
	}*/
	
	/*public Iterable<Address> findAllById(Iterable<Integer> ads) {
		return as.findAll(ads);
	}*/
	@Override
	public Iterable<Address> findAll() {
		return as.findAll();
	}
	
	@Override
	public Address findbyId(Integer id) {
		return as.findById(id);
	}

	@Override
	public void deletebyId(Integer id) {
		as.delete(id);
	}
	
	public void editAddress(Integer addressid, String addressline1, String addressline2, String city, String postalcode, Stateprovince sp) {
		
		if(!(city.isEmpty()) && !(postalcode.isEmpty()) && !(addressline1.isEmpty())) {
				
			Address ads = as.findById(addressid);
			
			ads.setAddressline1(addressline1);
			ads.setAddressline2(addressline2);
			ads.setCity(city);
			ads.setPostalcode(postalcode);
			
			ads.setModifieddate(LocalDate.now());
			sp = sps.save(sp);
			ads.setStateprovince(sp);
			
			save(ads);
			
		}else {
			throw new IllegalArgumentException("One of the arguments is invalid");
		}
		
	}

}
