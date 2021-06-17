package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Prenotazione;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long>{

	@Query(value="SELECT p "
			+ "FROM prenotazione p "
			+ "WHERE p.campo_id = ?.campo.id AND p.data = ?.data "
			+ "AND ((?.oraInizio > p.oraInizio AND ?.oraInizio < p.oraFine) OR "
			+ "(?.oraFine > p.oraInizio AND ?.oraFine < p.oraFine))", nativeQuery = true) 
	public List<Prenotazione> findPrenotazione(Prenotazione prenotazione);

}
