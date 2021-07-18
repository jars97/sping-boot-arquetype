package com.example.arquetype.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagedDTO {
	int page;
	int records;
	String orderBy;

}
