package com.icesi.edu.Stiven.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.icesi.edu.Stiven.model.person.Addresstype;

@Repository
public interface AddressTypeRepository extends CrudRepository<Addresstype, Integer> {

}
