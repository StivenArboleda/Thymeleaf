package com.icesi.edu.Stiven.repositories;

import org.springframework.data.repository.CrudRepository;
import com.icesi.edu.Stiven.model.person.UserModel;

public interface UserRepository extends CrudRepository<UserModel,Long> {
	
	UserModel findByUserName(String userName);
	
}
