package com.icesi.edu.Stiven.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.icesi.edu.Stiven.DAO.CustomerDAO;
import com.icesi.edu.Stiven.DAO.PersonDAO;
import com.icesi.edu.Stiven.DAO.StoreDAO;
import com.icesi.edu.Stiven.model.person.Person;
import com.icesi.edu.Stiven.model.sales.Customer;
import com.icesi.edu.Stiven.model.sales.Store;
import com.icesi.edu.Stiven.service.inter.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	CustomerDAO cd;
	PersonDAO pd;
	StoreDAO sd;

	public CustomerService(CustomerDAO cd, PersonDAO pd, StoreDAO sd) {
		this.cd = cd;
		this.pd = pd;
		this.sd = sd;
	}

	@Override
	public <S extends Customer> S save(S customer) {
		Customer nc = customer;
		Person p = pd.findById(nc.getPersonid());
		Store s = sd.findById(nc.getStoreid1());
		
		if (p != null) {
			if (s != null) {
				p.addCustomer(customer);
				s.addCustomer(customer);
				sd.save(s);
				pd.save(p);
				return (S) cd.save(customer);
			}else {
				throw new EntityNotFoundException("Store not valid");
			}
		}else {
			throw new EntityNotFoundException("Person id not valid");
		}
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
	public void editCustomer(Customer o) {
		Customer nc = o;
		Person p = pd.findById(nc.getPersonid());
		Store s = sd.findById(nc.getStoreid1());
		
		if (p != null) {
			if (s != null) {
				cd.update(o);
			}else {
				throw new EntityNotFoundException("Store not valid");
			}
		}else {
			throw new EntityNotFoundException("Person id not valid");
		}
		cd.update(o);
	}

	
}
