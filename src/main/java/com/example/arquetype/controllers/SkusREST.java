package com.example.arquetype.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arquetype.entity.Skus;
import com.example.arquetype.services.SkusService;

@RestController
@RequestMapping(value = "/skus")
public class SkusREST extends AbstractController<Skus, SkusService>{

	protected SkusREST(SkusService service) {
		super(service);
	}

}
