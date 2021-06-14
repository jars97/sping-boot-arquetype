package com.example.demo.services;


import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Bancos;

public interface BancosService {
	List<Bancos> findAll();
    Optional<Bancos> findById(Long id);
    Bancos save(Bancos tutenUnitType);
    void delete(Long id);
    Page<Bancos> findAllPaginated(Pageable pageable);
}
