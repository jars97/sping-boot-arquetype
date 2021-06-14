package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Bancos;

public interface BancosRepository extends JpaRepository<Bancos, Long>{
	
}
