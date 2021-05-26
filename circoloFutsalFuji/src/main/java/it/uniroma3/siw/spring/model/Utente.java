package it.uniroma3.siw.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cognome;

	@Column(nullable = false)
	private String email;
	
	private String telefono;
	
	public Utente(String nome, String cognome, String email, String telefono) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.telefono = telefono;
	}
}
