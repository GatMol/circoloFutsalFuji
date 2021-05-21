package it.uniroma3.siw.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public @Data class Campo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NonNull
	@Column(nullable = false)
	private Integer lunghezza;
	
	@NonNull
	@Column(nullable = false)
	private Integer larghezza;
	
	@NonNull
	@Column(nullable = false)
	private String tipoCampo;
	
	@NonNull
	@Column(nullable = false)
	private String tipoTerreno;
	
	@NonNull
	@Column(nullable = false)
	private Integer prezzo;
	
	@OneToMany
	@JoinColumn(name = "campo_id")
	private Map<Long,Prenotazione> prenotazioni;

	public void aggiungiPrenotazione(Prenotazione pc) {
		this.prenotazioni.put(pc.getId(), pc);
		
	}
}
