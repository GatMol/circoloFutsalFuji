package it.uniroma3.siw.circoloFutsalFuji.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.circoloFutsalFuji.model.Gestore;

public interface GestoreRepository  extends CrudRepository<Gestore, Long> {
	
	public Gestore findByEmail(String email);

}
