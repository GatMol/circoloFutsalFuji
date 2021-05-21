package it.uniroma3.siw.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class Money {
	
	private Float quantita;
	
	public void addMoney(Float daAggiungere) {
		this.quantita += daAggiungere;
	}
	
	public void diffMoney(Float daRimuovere) {
		this.quantita += daRimuovere;
	}
}
