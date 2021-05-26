package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.model.Campo;
import it.uniroma3.siw.repository.CampoRepository;

public class CampoService {

	@Autowired
	private CampoRepository campoRepository;
	
	public Campo campoPerId(Long id) {
		Optional<Campo> optional = this.campoRepository.findById(id);
		if(optional.isPresent()) 
			return optional.get();
		else
			return null;
	}

}
