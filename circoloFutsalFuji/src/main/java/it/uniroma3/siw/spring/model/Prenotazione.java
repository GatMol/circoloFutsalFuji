package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
public @Data class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@Column(nullable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate data;
	
	@Column(nullable = false)
	private LocalTime orarioInizio;
	
	@Column(nullable = false)
	private LocalTime orarioFine;
	
	@ManyToOne
	private Utente utente;

	public Utente creaEAggiungiUtente(String nome, String cognome, String email, String telefono) {
		Utente u = new Utente(nome, cognome, email, telefono);
		this.utente = u;
		return u;
	}
}
