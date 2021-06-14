package com.example.demo.services;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Notas;
import com.example.demo.repository.NotasRepository;

@Service
public class NotasService extends AbstractService<Notas, NotasRepository>{

	public NotasService(NotasRepository repository) {
		super(repository);
	}

}
