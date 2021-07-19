package com.example.arquetype.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.arquetype.dto.PagedDTO;
import com.example.arquetype.entity.AbstractEntity;

import io.swagger.annotations.ApiOperation;

public interface CommonController<E extends AbstractEntity> {
	@ApiOperation("Find by primary key")
	@GetMapping(value = "/{id}",produces = "application/json")
	ResponseEntity<E> findById(@PathVariable Long id);
	
	@ApiOperation("List all records")
	@GetMapping(value = "/",produces = "application/json")
    public ResponseEntity<List<E>> findAll(@RequestParam(required = false) String orderBy);
	
	@ApiOperation("Create new record")
	@PostMapping(value = "/",produces = "application/json")
    public ResponseEntity<E> save(@RequestBody E entity);
	
	@ApiOperation("Update a record, by primary key")
	@PutMapping(value = "/{id}", produces = "application/json",consumes = "application/json")
    public ResponseEntity<E> update(@PathVariable("id") Long id, @RequestBody E entity);
	
	@ApiOperation("Delete by primary key")
	@DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<E> deleteById(@PathVariable("id") Long id);
	
	@ApiOperation("List records paginated")
	@PostMapping(value = "/listallpaginated",produces = "application/json")
    public ResponseEntity<Object> findAllPaginatedPost(@RequestBody PagedDTO body);
}
