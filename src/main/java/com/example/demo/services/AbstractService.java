package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.AbstractEntity;
import com.example.demo.entity.Bancos;
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

	@Override
	public E save(E entity) {
		return this.repository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		this.repository.deleteById(id);		
	}

	@Override
	public Page<E> findAllPaginated(Pageable pageable) {
		return this.repository.findAll(pageable);
	}
    
    
}
