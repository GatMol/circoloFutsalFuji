package it.uniroma3.siw.spring.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Prenotazione;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long>{

	@Query(value="SELECT * "
			+ "FROM prenotazione p "
			+ "WHERE p.campo_id = ?1 AND p.data = ?2 "
			+ "AND ((?3 > p.orario_Inizio AND ?3 < p.orario_Fine) OR "
			+ "(?4 > p.orario_Inizio AND ?4 < p.orario_Fine))", nativeQuery = true) 
	public List<Prenotazione> findPrenotazione(Long campo_id, LocalDate data, LocalTime oraInizio,
												LocalTime oraFine);

}
