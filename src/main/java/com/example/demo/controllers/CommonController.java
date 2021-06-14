package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.AbstractEntity;

public interface CommonController<E extends AbstractEntity> {
	
	@GetMapping(value = "/{id}",produces = "application/json")
	ResponseEntity<E> findById(@PathVariable Long id);
	
	@GetMapping(value = "/",produces = "application/json")
    public ResponseEntity<List<E>> findAll();
}
