package it.uniroma3.siw.circoloFutsalFuji.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.circoloFutsalFuji.model.Gestore;
import it.uniroma3.siw.circoloFutsalFuji.repository.GestoreRepository;

@Service
public class GestoreService {
	
	@Autowired
	private GestoreRepository gestoreRepository;
	
	@Transactional
	public Gestore inserisci(Gestore gestore) {
		return gestoreRepository.save(gestore);
	}

}
