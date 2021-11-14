package com.icesi.edu.Stiven.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icesi.edu.Stiven.model.person.Personphone;
import com.icesi.edu.Stiven.model.person.PersonphonePK;

@Repository
public interface PersonPhoneRepository extends CrudRepository<Personphone, PersonphonePK>{

}
