package com.icesi.edu.Stiven.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icesi.edu.Stiven.model.person.Countryregion;


public interface CountryRegionRepository extends CrudRepository<Countryregion, String>{

}
