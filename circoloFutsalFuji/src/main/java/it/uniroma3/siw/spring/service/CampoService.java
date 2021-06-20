package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.repository.CampoRepository;

@Service
public class CampoService {

	@Autowired
	private CampoRepository campoRepository;
	
	@Transactional
	public Campo inserisci(Campo c) {
		return this.campoRepository.save(c);
	}
	
	@Transactional
	public Campo campoPerId(Long id) {
		Optional<Campo> optional = this.campoRepository.findById(id);
		if(optional.isPresent()) 
			return optional.get();
		else
			return null;
	}

	@Transactional
	public List<Campo> tutti() {
		return (List<Campo>) this.campoRepository.findAll();
	}

	@Transactional
	public void rimuoviCampo(Campo c) {
		this.campoRepository.delete(c);
	}

	@Transactional
	public void rimuoviCampoPerId(Long id) {
		this.campoRepository.deleteById(id);
	}

	@Transactional
	public void rimuoviTutti() {
		this.campoRepository.deleteAll();
		
	}
}
