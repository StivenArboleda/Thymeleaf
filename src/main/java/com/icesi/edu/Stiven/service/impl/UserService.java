package com.icesi.edu.Stiven.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.model.person.UserModel;
import com.icesi.edu.Stiven.model.person.UserType;
import com.icesi.edu.Stiven.repositories.UserRepository;
import com.icesi.edu.Stiven.service.inter.IUserService;

@Service
public class UserService implements IUserService{
	
	private UserRepository userRepo;

	public UserService(UserRepository userRepo) {

		this.userRepo = userRepo;
	}
	
	public <S extends UserModel> S save(S user) {
		if (user.getId() == 0 || user.getPassword() == null) {
			if(user.getId() == 0) {
				throw new IllegalArgumentException("The id user is empty.");
			} else if(user.getPassword() == null) {
				throw new IllegalArgumentException("The password is empty.");
			} 
		}
		userRepo.save(user);
		return user;
	}

	public <S extends UserModel> Iterable<S> saveAll(Iterable<S> user) {
		for (UserModel us : user) {
			save(us);
		}
		return user;
	}

	public Optional<UserModel> findById(long id) {
		return userRepo.findById(id);
	}

	public boolean existsById(long id) {
		return userRepo.existsById(id);
	}

	public Iterable<UserModel> findAll() {
		return userRepo.findAll();
	}

	public long count() {
		return userRepo.count();
	}

	public void deleteAll() {
		userRepo.deleteAll();
	}

	public void delete(UserModel us) {
		userRepo.delete(us);
	}

	public void deleteAll(Iterable<? extends UserModel> us) {
		userRepo.deleteAll(us);
	}

	public void deleteById(long id) {
		userRepo.deleteById(id);
	}
	public void editPerson(long id, String userName, String password, UserType type) {
		if (id == 0 || password == null) {
			throw new IllegalArgumentException("Verify one of the arguments is not valid.");
		}
		UserModel urii = userRepo.findById(id).get();
		urii.setUserName(userName);
		urii.setPassword(password);
		urii.setType(type);
		
		save(urii);
	}
	
}
