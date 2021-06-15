package com.example.demo.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "renglones")
public class Renglones {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	
	@Column(name = "renglon_id")
    private Long renglonId;
	
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="factura_id")
    private Facturas factura;
	
	
}
