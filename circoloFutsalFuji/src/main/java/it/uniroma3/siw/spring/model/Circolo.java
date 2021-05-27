package it.uniroma3.siw.spring.model;

import java.util.Map;

import it.uniroma3.siw.spring.service.PortaleCircolo;
import lombok.Data;

public @Data class Circolo {
	
	private String nome;
	
	private String indirizzo;
	
	private String telefono;
	
	private String email;
	
	private Map<Long, Campo> campi;
	private PortaleCircolo portale;
	
	public Campo getCampo(Long idCampo) {
		return this.campi.get(idCampo);
	}
}
