package it.uniroma3.siw.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {

	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	
	@Transactional
	public Prenotazione inserisci(Prenotazione prenotazione) {
		return prenotazioneRepository.save(prenotazione);
	}
	
	@Transactional
	public List<Prenotazione> tutteLePrenotazioni(){
		return (List<Prenotazione>) prenotazioneRepository.findAll(); 
	}
	
	@Transactional
	public boolean alreadyExists(Long campo_id, Prenotazione prenotazione) {
		List<Prenotazione> prenotazioni = prenotazioneRepository.findPrenotazione(campo_id, prenotazione.getData(),
																				  prenotazione.getOrarioInizio(), prenotazione.getOrarioFine());
		return prenotazioni.size() > 0;
	}

	@Transactional
	public Prenotazione prenotazionePerCodice(String codiceConferma) {
		return this.prenotazioneRepository.findByCodice(codiceConferma);
	}
	
	@Transactional
	public void rimuoviPrenotazioniNonConfermate() {
		this.prenotazioneRepository.deleteAfter10Minutes();
	}
}
