package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Renglones;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacturasDTO {
	private Long id;
	private String cliente;
	private List<Renglones> renglones;
}
