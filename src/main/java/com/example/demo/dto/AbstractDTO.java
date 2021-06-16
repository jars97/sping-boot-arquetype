package com.example.demo.dto;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractDTO {
	private Long id;
}
