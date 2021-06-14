package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.AbstractEntity;
import com.example.demo.repository.CommonRepository;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>>implements CommonService<E> {
	protected final R repository;
	
    @Autowired
    protected AbstractService(R repository){
        this.repository = repository;
    }

	@Override
	public Optional<E> findById(Long id) {
		return this.repository.findById(id);
	}

	@Override
	public List<E> findAll() {
		return this.repository.findAll();
	}
    
    
}
