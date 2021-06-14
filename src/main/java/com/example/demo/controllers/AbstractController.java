package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.demo.entity.AbstractEntity;
import com.example.demo.services.CommonService;

public abstract class AbstractController <E extends AbstractEntity, S extends CommonService<E>> implements CommonController<E>{
	private final S service;
	
	
    @Autowired
    protected AbstractController(S service){
        this.service = service;
    }
    
	@Override
	public ResponseEntity<E> findById(Long id) {
		Optional<E> response = this.service.findById(id);
		if (response.isPresent()) {
			return ResponseEntity.ok(response.get());
		}else {
			return null;
		}
		
	}

	@Override
	public ResponseEntity<List<E>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}
	
}
