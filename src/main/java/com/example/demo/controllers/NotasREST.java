package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Notas;
import com.example.demo.services.NotasService;

@RestController
@RequestMapping(value = "/notas")
public class NotasREST extends AbstractController<Notas, NotasService>{

	protected NotasREST(NotasService service) {
		super(service);
	}

}
