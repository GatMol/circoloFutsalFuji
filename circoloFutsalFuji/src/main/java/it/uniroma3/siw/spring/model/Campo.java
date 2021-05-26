package it.uniroma3.siw.spring.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
public @Data class Campo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private Integer lunghezza;
	
	@Column(nullable = false)
	private Integer larghezza;
	
	@Column(nullable = false)
	private String tipoCampo;
	
	@Column(nullable = false)
	private String tipoTerreno;
	
	@Column(nullable = false)
	private Integer prezzo;
	
	@OneToMany
	@JoinColumn(name = "campo_id")
	private Map<Long,Prenotazione> prenotazioni;

	public void aggiungiPrenotazione(Prenotazione pc) {
		this.prenotazioni.put(pc.getId(), pc);
		
	}
}
