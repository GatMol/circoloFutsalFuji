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
	public boolean alreadyExists(Prenotazione prenotazione) {
		List<Prenotazione> prenotazioni = prenotazioneRepository.findPrenotazione(prenotazione);
		if (prenotazioni.size() > 0)
			return true;
		else 
			return false;
	}

	@Transactional
	public Prenotazione prenotazionePerCodice(String codiceConferma) {
		return this.prenotazioneRepository.findByCodice(codiceConferma);
	}
	
	@Transactional
	public void rimuoviPrenotazioniNonConfermate() {
		this.prenotazioneRepository.deleteAfter30Minutes();
	}
}
