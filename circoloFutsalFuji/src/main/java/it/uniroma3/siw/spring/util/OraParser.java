package it.uniroma3.siw.spring.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.validation.BindingResult;

import it.uniroma3.siw.spring.model.Prenotazione;

public class OraParser {
	
	public static void effettuaParse(String hinizio, String hfine, Prenotazione prenotazione, BindingResult bindingResult) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			prenotazione.setOrarioInizio(LocalTime.parse(hinizio, formatter));
			prenotazione.setOrarioFine(LocalTime.parse(hfine, formatter));
		}catch(DateTimeParseException e){
			bindingResult.reject("oravuota");
		}
	}

}
