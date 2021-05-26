package it.uniroma3.siw.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Gestore;
import it.uniroma3.siw.spring.repository.GestoreRepository;

@Service
public class GestoreService {
	
	@Autowired
	private GestoreRepository gestoreRepository;
	
	@Transactional
	public Gestore inserisci(Gestore gestore) {
		return gestoreRepository.save(gestore);
	}

}
