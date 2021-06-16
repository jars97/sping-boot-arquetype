package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.BancosDTO;
import com.example.demo.entity.AbstractEntity;

public interface CommonController<E extends AbstractEntity> {
	
	@GetMapping(value = "/{id}",produces = "application/json")
	ResponseEntity<E> findById(@PathVariable Long id);
	
	@GetMapping(value = "/",produces = "application/json")
    public ResponseEntity<List<E>> findAll();
	
	@PostMapping(value = "/",produces = "application/json")
    public ResponseEntity<E> save(@RequestBody E entity);
	
	@PutMapping(value = "/{id}", produces = "application/json",consumes = "application/json")
    public ResponseEntity<E> update(@PathVariable("id") Long id, @RequestBody E entity);
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<E> deleteById(@PathVariable("id") Long id);
	
	@GetMapping(value = "/page/{page}/records/{records}",produces = "application/json")
    public ResponseEntity<Object> findAllPaginated(@PathVariable("page") int page, @PathVariable("records") int records);
}
