package it.uniroma3.siw.spring.controller.validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Prenotazione;

@Component
public class PrenotazioneValidator implements Validator{

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "data", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orarioInizio", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orarioFine", "required");

		Prenotazione prenotazione = (Prenotazione) o;
		if(!errors.hasErrors()) {
			if(prenotazione.getData().isBefore(LocalDate.now()))
				errors.reject("data.passata");

			if(prenotazione.getData().isEqual(LocalDate.now())) {

				if(prenotazione.getOrarioInizio().isBefore(LocalTime.now()))
					errors.reject("ora.passata");

				if(prenotazione.getOrarioFine().isBefore(LocalTime.now()))
					errors.reject("ora.passata");
			}
			if(prenotazione.getOrarioFine().isBefore(prenotazione.getOrarioInizio()))
				errors.reject("orafine.prima");
			
			if(prenotazione.getOrarioInizio().getHour() < 8 || prenotazione.getOrarioInizio().getHour() > 22)
				errors.reject("oraInizio.oltre");
			
			if(prenotazione.getOrarioFine().getHour() < 9 || prenotazione.getOrarioInizio().getHour() > 23)
				errors.reject("oraFine.oltre");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Prenotazione.class.equals(clazz);
	}
	
	public void effettuaParse(String hinizio, String hfine, Prenotazione prenotazione, BindingResult bindingResult) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			prenotazione.setOrarioInizio(LocalTime.parse(hinizio, formatter));
			prenotazione.setOrarioFine(LocalTime.parse(hfine, formatter));
		}catch(DateTimeParseException e){
			bindingResult.reject("oravuota");
		}
	}

}
