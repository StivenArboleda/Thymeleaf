package com.icesi.edu.Stiven.service.impl;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.DAO.CustomerDAO;
import com.icesi.edu.Stiven.model.sales.Customer;
import com.icesi.edu.Stiven.service.inter.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	CustomerDAO cd;

	public CustomerService(CustomerDAO cd) {
		this.cd = cd;
	}

	@Override
	public <S extends Customer> S save(S customer) {
		return (S) cd.save(customer);
	}

	@Override
	public <S extends Customer> Iterable<S> saveAll(Iterable<S> a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findbyId(Integer id) {
		return cd.findById(id);
	}

	@Override
	public Iterable<Customer> findAll() {
		return cd.findAll();
	}

	@Override
	public void deletebyId(Integer id) {
		cd.deletebyId(id);
	}

	public void delete(Customer c) {
		cd.delete(c);
	}

	@Override
	public void editStore(Customer o) {
		cd.update(o);
	}

	
}
