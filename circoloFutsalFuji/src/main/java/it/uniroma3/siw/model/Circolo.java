package it.uniroma3.siw.model;

import java.util.Map;

import it.uniroma3.siw.service.PortaleCircolo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public @Data class Circolo {
	
	@NonNull
	private String nome;
	
	@NonNull
	private String indirizzo;
	
	@NonNull
	private String telefono;
	
	@NonNull
	private String email;
	
	private Map<Long, Campo> campi;
	private PortaleCircolo portale;
	
	public Campo getCampo(Long idCampo) {
		return this.campi.get(idCampo);
	}
}
