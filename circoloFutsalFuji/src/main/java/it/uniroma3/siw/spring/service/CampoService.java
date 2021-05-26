package it.uniroma3.siw.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.repository.CampoRepository;

@Service
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
