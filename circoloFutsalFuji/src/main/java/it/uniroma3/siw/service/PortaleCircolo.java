package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Campo;
import it.uniroma3.siw.model.Circolo;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Utente;

@Service
public class PortaleCircolo {
	
	private Utente utenteCorrente;
	private Campo campoCorrente;
	private Circolo circolo;
	private Prenotazione prenotazioneCorrente;
	
	public void iniziaPrenotazione() {
		//TODO: Richiesta GET quando clicchi sul pulsante per la prenotazione
		//creo la prenotazione e la metto nel model e tramite thymeleaf aggiungo 
		this.prenotazioneCorrente = new Prenotazione();
	};
	
	public void selezionaCampo(Long idCampo) {
		this.campoCorrente = this.circolo.getCampo(idCampo);
	}
	
	public void inserisciDatiUtenteEPeriodo(String nome, String cognome, String email, String telefono, LocalDate data, LocalTime orarioInizio, LocalTime orarioFine) {
		this.utenteCorrente = this.prenotazioneCorrente.creaEAggiungiUtente(nome,cognome,email,telefono);
		this.prenotazioneCorrente.setData(data);
		this.prenotazioneCorrente.setOrarioInizio(orarioInizio);
		this.prenotazioneCorrente.setOrarioFIne(orarioFine);
	}
	
	public void confermaPrenotazione() {
		this.campoCorrente.aggiungiPrenotazione(this.prenotazioneCorrente);
	}
}
