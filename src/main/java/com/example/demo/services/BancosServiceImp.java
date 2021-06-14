package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bancos;
import com.example.demo.repository.BancosRepository;
import org.springframework.data.domain.Pageable;

@Service
public class BancosServiceImp implements BancosService{
	
	@Autowired
	BancosRepository repo;
	
	@Override
	public List<Bancos> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Bancos> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public Bancos save(Bancos item) {
		return repo.save(item);
	}
	
	@Override
	public Page<Bancos> findAllPaginated(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}



}
