package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import javax.persistence.CascadeType;
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
	
	@Column(unique = true, nullable = false)
	private String codice;
	
	@Column(nullable = false)
	private boolean confermata;
	
	@Column(nullable = false)
	private LocalDateTime dataDiCreazione;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Utente utente;
	
	public Prenotazione() {
		this.confermata = false;
		this.codice = getUniqueCode();
	}
	
	public Prenotazione(LocalDate data, LocalTime orarioInizio, LocalTime orarioFine,
			LocalDateTime dataDiCreazione) {
		this();
		this.data = data;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		this.dataDiCreazione = dataDiCreazione;
	}
	
	private String getUniqueCode() {
		return UUID.randomUUID().toString();
	}
}