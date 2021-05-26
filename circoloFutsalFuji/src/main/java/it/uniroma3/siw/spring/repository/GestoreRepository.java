package it.uniroma3.siw.spring.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Gestore;

public interface GestoreRepository  extends CrudRepository<Gestore, Long> {
	
	public Gestore findByEmail(String email);

}
