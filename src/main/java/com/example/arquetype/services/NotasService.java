package com.example.arquetype.services;
import org.springframework.stereotype.Service;
import com.example.arquetype.entity.Notas;
import com.example.arquetype.repository.NotasRepository;

@Service
public class NotasService extends AbstractService<Notas, NotasRepository>{

	public NotasService(NotasRepository repository) {
		super(repository);
	}

}
