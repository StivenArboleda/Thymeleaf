package com.icesi.edu.Stiven.DAO;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    
    T findById(Integer id);
        
    List<T> findAll();
    
    T save(T t);
    
    void update(T t);
    
    void delete(T t);
}