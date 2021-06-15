package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Facturas;
import com.example.demo.repository.FacturasRepository;

import org.springframework.data.domain.Pageable;

@Service
public class FacturasServiceImp implements FacturasService{
	
	@Autowired
	FacturasRepository repo;
	
	@Override
	public List<Facturas> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Facturas> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public Facturas save(Facturas item) {
		return repo.save(item);
	}
	
	@Override
	public Page<Facturas> findAllPaginated(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}



}
