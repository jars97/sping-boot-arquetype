package com.example.arquetype.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.arquetype.dto.PagedDTO;
import com.example.arquetype.entity.AbstractEntity;
import com.example.arquetype.services.CommonService;

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
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<E>> findAll(String orderBy) {
		if (orderBy!=null) {
			return ResponseEntity.ok(this.service.findAll(orderBy));
		}else {
			return ResponseEntity.ok(this.service.findAll());
		}
	}	
	
	@Override
	public ResponseEntity<Object> findAllPaginatedPost(PagedDTO body) {
		Pageable paging = PageRequest.of(body.getPage()-1, body.getRecords(),Sort.by(body.getOrderBy()));
        Page<E> mList = this.service.findAllPaginated(paging);
        Map<String, Object> response = new HashMap<>();
        response.put("data", mList.getContent());
        response.put("currentPage", mList.getNumber()+1);
        response.put("totalItems", mList.getTotalElements());
        response.put("totalPages", mList.getTotalPages());
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<E> save(E entity) {
		return  ResponseEntity.ok(this.service.save(entity));
	}
	
	@Override
	public ResponseEntity<E> update(Long id, E entity) {
		Optional<E> response = this.service.findById(id);
		if (response.isPresent()) {
			return ResponseEntity.ok(this.service.save(entity));
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<E> deleteById(Long id) {
		Optional<E> response = this.service.findById(id);
		if (response.isPresent()) {
			this.service.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
