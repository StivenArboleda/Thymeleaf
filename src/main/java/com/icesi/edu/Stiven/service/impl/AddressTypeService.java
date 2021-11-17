package com.icesi.edu.Stiven.service.impl;


import java.util.Optional;
import org.springframework.stereotype.Service;
import com.icesi.edu.Stiven.model.person.Addresstype;
import com.icesi.edu.Stiven.repositories.AddressTypeRepository;
import com.icesi.edu.Stiven.service.inter.IAddressTypeService;


@Service
public class AddressTypeService implements IAddressTypeService{
	
	private AddressTypeRepository as;
	
	public AddressTypeService(AddressTypeRepository as) {
		this.as = as;
	}
	
	public <S extends Addresstype> S save(S address) {

		as.save(address);
				
		return address;
	}
	
	public <S extends Addresstype> Iterable<S> saveAll(Iterable<S> ads) {
		for (Addresstype addre : ads) {
			save(addre);
		}
		return ads;
	}
	
	public Optional<Addresstype> findById(Integer id) {
		return as.findById(id);
	}
	
	public boolean existsById(Integer id) {
		return as.existsById(id);
	}
	
	public Iterable<Addresstype> findAllById(Iterable<Integer> ads) {
		return as.findAllById(ads);
	}
	@Override
	public Iterable<Addresstype> findAll() {
		return as.findAll();
	}
	
	@Override
	public Addresstype findbyId(Integer id) {
		return as.findById(id).get();
	}

	@Override
	public void deletebyId(Integer id) {
		as.deleteById(id);
	}


	/*public void editAddress(Integer addressid) {
		
		if(!(city.isEmpty()) && !(postalcode.isEmpty()) && !(addressline1.isEmpty())) {
			
						
			Address ads = as.findById(addressid).get();
			
			ads.setAddressline1(addressline1);
			ads.setAddressline2(addressline2);
			ads.setCity(city);
			ads.setPostalcode(postalcode);
			
			sp = sps.saveCorrect(sp);
			ads.setStateprovince(sp);
			
			save(ads);
			
		}else {
			throw new IllegalArgumentException("One of the arguments is invalid");
		}
		
	}*/
}
