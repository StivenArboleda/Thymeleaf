package com.icesi.edu.Stiven.service.inter;

import java.util.Optional;

import com.icesi.edu.Stiven.model.person.UserModel;
import com.icesi.edu.Stiven.model.person.UserType;

public interface IUserService {
	
		
	public <S extends UserModel> S save(S user);

	public <S extends UserModel> Iterable<S> saveAll(Iterable<S> user);

	public Optional<UserModel> findById(long id);

	public boolean existsById(long id);

	public Iterable<UserModel> findAll();

	public long count();

	public void deleteAll();

	public void delete(UserModel us);

	public void deleteAll(Iterable<? extends UserModel> us);

	public void deleteById(long id);
	
	public void editPerson(long id, String username, String password, UserType type);
	
}
