package com.example.arquetype.services;
import org.springframework.stereotype.Service;
import com.example.arquetype.entity.Skus;
import com.example.arquetype.repository.SkusRepository;

@Service
public class SkusService extends AbstractService<Skus, SkusRepository>{

	public SkusService(SkusRepository repository) {
		super(repository);
	}

}
