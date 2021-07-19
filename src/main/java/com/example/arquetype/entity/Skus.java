package com.example.arquetype.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tuten_sku")
public class Skus extends AbstractEntity {
	@Id
	@Column(name = "sku_id")
	Long skuId ;
	
	@Column(name = "sku_name")
	String skuName ;
	 
	@Column(name = "sku_measure_unit") 
	String skuMeasureUnit;
	 
	@Column(name = "business_unit_id") 
	String businessUnitId;
	
	@Column(name = "sku_services") 
	String skuServices;

	@Column(name = "sku_owner") 
	Integer skuOwner;
}
