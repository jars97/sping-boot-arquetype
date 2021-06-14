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
import com.example.demo.dto.BancosDTO;
import com.example.demo.dto.ErrorDTO;
import com.example.demo.entity.Bancos;
import com.example.demo.services.BancosService;

@RestController
@RequestMapping(value ="/bancos" )
public class BancosREST {
	@Autowired 
	BancosService service;
	
	@Autowired
    ModelMapper modelMapper;
	
	@GetMapping(value = "/page/{page}/records/{records}",
            produces = "application/json")
    public ResponseEntity<Object> findAllPaginated(
            @PathVariable("page") int page,
            @PathVariable("records") int records) {



        List<BancosDTO> listResponse = new ArrayList<>();
        Pageable paging = PageRequest.of(page-1, records,Sort.by("id"));
        Page<Bancos> mList = this.service.findAllPaginated(paging);
        		
        List<Bancos> listR = mList.getContent();
        if (!listR.isEmpty()) {
            for (Bancos item:listR) {
                BancosDTO dto = modelMapper.map(item,BancosDTO.class);
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
	
	@GetMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<Object> find(
            @PathVariable("id") Long id) {
		Optional<Bancos> response = this.service.findById(id);
		BancosDTO dto = new BancosDTO();
        if (response.isPresent()){
            dto=modelMapper.map(response.get(),BancosDTO.class);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	@PostMapping(value = "/", produces = "application/json",consumes = "application/json")
    public ResponseEntity<Object> add(
            @RequestBody BancosDTO body) {
        try {
            Bancos item = modelMapper.map(body,Bancos.class);
            Bancos response=this.service.save(item);
            BancosDTO dto = modelMapper.map(response,BancosDTO.class);
            return new ResponseEntity<>(dto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping(value = "/{id}", produces = "application/json",consumes = "application/json")
    public ResponseEntity<Object> updateMotors(
            @PathVariable("id") Long id,
            @RequestBody BancosDTO body) {
       
		 try {
	            Optional<Bancos> item = this.service.findById(id);
	            if (item.isPresent()){
	            	Bancos banco = modelMapper.map(body,Bancos.class);
	            	banco.setId(id);
	            	Bancos response=this.service.save(banco);
	            	BancosDTO dto = modelMapper.map(response,BancosDTO.class);
	                return new ResponseEntity<>(dto,HttpStatus.OK);
	            }else{
	            	return new ResponseEntity<>(new ErrorDTO(Messages.NO_RECORD_FOUND), HttpStatus.BAD_REQUEST);
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
