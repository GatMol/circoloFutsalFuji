package it.uniroma3.siw.spring.model;

import java.util.List;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@Column(nullable = false)
	private String img1;
	
	@Column(nullable = false)
	private String img2;
	
	@OneToMany
	@JoinColumn(name = "campo_id")
	private List<Prenotazione> prenotazioni;

	public void aggiungiPrenotazione(Prenotazione pc) {
		this.prenotazioni.add(pc);
	}
	
	public String getPhotoImage1Path() {
		if(this.img1 == null || this.id == null) return null;
		return "/campo-photos/" + id + "/" + this.img1;
	}
	
	public String getPhotoImage2Path() {
		if(this.img2 == null || this.id == null) return null;
		return "/campo-photos/" + id + "/" + this.img2;
	}
}
