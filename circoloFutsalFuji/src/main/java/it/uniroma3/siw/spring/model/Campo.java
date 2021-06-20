package it.uniroma3.siw.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
public @Data class Campo {
	
	public Campo() {
		this.prenotazioni = new ArrayList<Prenotazione>();
	}
	
	@Id
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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
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
