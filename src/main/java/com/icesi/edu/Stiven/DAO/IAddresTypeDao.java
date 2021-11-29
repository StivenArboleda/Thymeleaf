package com.icesi.edu.Stiven.DAO;

import java.util.List;
import com.icesi.edu.Stiven.model.person.Addresstype;

public interface IAddresTypeDao {
	
    Addresstype findById(Integer id);
    
    List<Addresstype> findAll();
    
    Addresstype save(Addresstype t);
    
    void update(Addresstype t);
    
    void delete(Addresstype t);
	
}
