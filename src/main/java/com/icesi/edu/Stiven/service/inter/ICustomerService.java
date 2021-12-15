package com.icesi.edu.Stiven.service.inter;

import com.icesi.edu.Stiven.model.sales.Customer;

public interface ICustomerService {
	public <S extends Customer> S save(S customer);
	
	public <S extends Customer> Iterable<S> saveAll(Iterable<S> a);
	
	public Customer findbyId(Integer id);
		
	public Iterable<Customer> findAll();
	
	public void deletebyId(Integer id);
	
	public void editCustomer(Customer o);
}
