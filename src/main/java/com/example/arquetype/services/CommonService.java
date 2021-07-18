package com.example.arquetype.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.arquetype.entity.AbstractEntity;

public interface CommonService<E extends AbstractEntity> {
	Optional<E> findById(Long id);
	List<E> findAll(String orderBy);
	E save(E entity);
	void deleteById(Long id);
	Page<E> findAllPaginated(Pageable pageable);
}
