package com.example.demo.entity;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "facturas")
public class Facturas {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cliente")
    private String cliente;
    
    @OneToMany(mappedBy="factura",cascade = CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
    @OrderBy("renglon_id")
    private List<Renglones> renglones;

}
