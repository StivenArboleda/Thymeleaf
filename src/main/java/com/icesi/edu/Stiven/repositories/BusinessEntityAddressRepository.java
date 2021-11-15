package com.icesi.edu.Stiven.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icesi.edu.Stiven.model.person.Businessentityaddress;
import com.icesi.edu.Stiven.model.person.BusinessentityaddressPK;

@Repository
public interface BusinessEntityAddressRepository extends CrudRepository<Businessentityaddress, Integer>{

}
