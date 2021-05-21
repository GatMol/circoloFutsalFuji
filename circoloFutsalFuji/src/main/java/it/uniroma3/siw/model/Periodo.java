package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class Periodo {
	
	private LocalDate data;
	private LocalTime orarioInizio;
	private LocalTime orarioFine;
	
}
