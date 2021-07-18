package com.example.arquetype.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arquetype.entity.Notas;
import com.example.arquetype.services.NotasService;

@RestController
@RequestMapping(value = "/notas")
public class NotasREST extends AbstractController<Notas, NotasService>{

	protected NotasREST(NotasService service) {
		super(service);
	}

}
