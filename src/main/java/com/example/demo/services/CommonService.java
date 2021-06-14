package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.AbstractEntity;

public interface CommonService<E extends AbstractEntity> {
	Optional<E> findById(Long id);
	List<E> findAll();
}
