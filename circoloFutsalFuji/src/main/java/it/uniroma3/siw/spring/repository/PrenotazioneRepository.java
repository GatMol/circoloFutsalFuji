package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Prenotazione;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long>{

	
	@Query(value="SELECT *"
			+ "FROM prenotazione JOIN "
			+ "(SELECT prenotazione"
			+ "FROM campo"
			+ "WHERE campo.prenotazione = ?1.id AND prenotazione.data = ?1.data)"
			+ " ON prenotazione.id = prenotazione "
			+ "WHERE NOT(?1.oraInizio > oraInizio AND ?1.oraInizio < oraFine) AND "
			+ "NOT(?1.oraFine > oraInizio AND ?1.oraFine < oraFine) ") 
	public List<Prenotazione> findPrenotazione(Prenotazione prenotazione);
}
