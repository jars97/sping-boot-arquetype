package com.example.demo.services;


import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Facturas;

public interface FacturasService {
	List<Facturas> findAll();
    Optional<Facturas> findById(Long id);
    Facturas save(Facturas factura);
    void delete(Long id);
    Page<Facturas> findAllPaginated(Pageable pageable);
}
