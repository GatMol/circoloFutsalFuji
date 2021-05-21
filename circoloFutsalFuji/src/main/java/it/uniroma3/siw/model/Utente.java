package it.uniroma3.siw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public @Data class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NonNull
	@Column(nullable = false)
	private String nome;
	@NonNull
	@Column(nullable = false)
	private String cognome;
	@NonNull
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
