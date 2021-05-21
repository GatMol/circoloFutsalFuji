package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public @Data class Prenotazione {

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NonNull
	@Column(nullable = false)
	@ManyToOne
	private Utente utente;

	@NonNull
	@Column(nullable = false)
	private LocalDate data;
	
	@NonNull
	@Column(nullable = false)
	private LocalTime orarioInizio;
	
	@NonNull
	@Column(nullable = false)
	private LocalTime orarioFIne;

	public Utente creaEAggiungiUtente(String nome, String cognome, String email, String telefono) {
		Utente u = new Utente(nome, cognome, email, telefono);
		this.utente = u;
		return u;
	}
}
