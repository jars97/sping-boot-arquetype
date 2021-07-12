package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.ErrorDTO;
import com.example.demo.dto.FacturasDTO;
import com.example.demo.dto.FacturasDTO2;
import com.example.demo.entity.Facturas;
import com.example.demo.entity.Renglones;
import com.example.demo.services.FacturasService;

@RestController
@RequestMapping(value ="/facturas" )
public class FacturasREST {
	@Autowired 
	FacturasService service;
	
	@Autowired
    ModelMapper modelMapper;
	
	@GetMapping(value = "/page/{page}/records/{records}",
            produces = "application/json")
    public ResponseEntity<Object> findAllPaginated(
            @PathVariable("page") int page,
            @PathVariable("records") int records) {



        List<FacturasDTO> listResponse = new ArrayList<>();
        Pageable paging = PageRequest.of(page-1, records,Sort.by("id"));
        Page<Facturas> mList = this.service.findAllPaginated(paging);
        		
        List<Facturas> listR = mList.getContent();
        if (!listR.isEmpty()) {
            for (Facturas item:listR) {
            	FacturasDTO dto = modelMapper.map(item,FacturasDTO.class);
                listResponse.add(dto);
            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put("data", listResponse);
        response.put("currentPage", mList.getNumber());
        response.put("totalItems", mList.getTotalElements());
        response.put("totalPages", mList.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping(value = "/",
            produces = "application/json")
    public ResponseEntity<Object> findAll() {
        List<FacturasDTO2> listResponse = new ArrayList<>();
        List<Facturas> mList = this.service.findAll();
        		
        if (!mList.isEmpty()) {
            for (Facturas item:mList) {
            	FacturasDTO2 dto = modelMapper.map(item,FacturasDTO2.class);
                listResponse.add(dto);
            }
        }
        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }
	
	@GetMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<Object> find(
            @PathVariable("id") Long id) {
		Optional<Facturas> response = this.service.findById(id);
		FacturasDTO dto = new FacturasDTO();
        if (response.isPresent()){
            dto=modelMapper.map(response.get(),FacturasDTO.class);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }else {
        	return new ResponseEntity<>(new ErrorDTO(Messages.NO_RECORD_FOUND), HttpStatus.NOT_FOUND);
        }
        
    }

	@PostMapping(value = "/", produces = "application/json",consumes = "application/json")
    public ResponseEntity<Object> add(
            @RequestBody FacturasDTO body) {
        try {
        	Facturas factura = modelMapper.map(body,Facturas.class);
        	List<Renglones> mRrenglones = factura.getRenglones();
            for (Renglones item:mRrenglones) {
                item.setFactura(factura);
            }
        	Facturas response=this.service.save(factura);
            FacturasDTO dto = modelMapper.map(response,FacturasDTO.class);
            return new ResponseEntity<>(dto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping(value = "/{id}", produces = "application/json",consumes = "application/json")
    public ResponseEntity<Object> updateMotors(
            @PathVariable("id") Long id,
            @RequestBody FacturasDTO body) {
       
		 try {
	            Optional<Facturas> item = this.service.findById(id);
	            if (item.isPresent()){
	            	Facturas factura = item.get();
	            	factura.getRenglones().clear();
	            	factura.getRenglones().addAll(body.getRenglones());
	            	factura.setCliente(body.getCliente());
	            	List<Renglones> mRrenglones = factura.getRenglones();
	                for (Renglones renglon:mRrenglones) {
	                	renglon.setFactura(factura);
	                }
	            	Facturas response=this.service.save(factura);
	            	FacturasDTO dto = modelMapper.map(response,FacturasDTO.class);
	                return new ResponseEntity<>(dto,HttpStatus.OK);
	            }else{
	            	return new ResponseEntity<>(new ErrorDTO(Messages.NO_RECORD_FOUND), HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
    }
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteMotors(
            @PathVariable("id") Long id) {
        try {
            this.service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(Messages.DELETE_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
