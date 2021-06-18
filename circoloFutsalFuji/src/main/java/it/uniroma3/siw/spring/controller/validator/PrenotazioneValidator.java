package it.uniroma3.siw.spring.controller.validator;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;
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
		if(prenotazione.getData().isBefore(LocalDate.now()))
			errors.reject("data.passata");
		
		if(prenotazione.getOrarioInizio().isBefore(LocalTime.now()))
			errors.reject("ora.passata");
		
		if(prenotazione.getOrarioFine().isBefore(LocalTime.now()))
			errors.reject("ora.passata");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Prenotazione.class.equals(clazz);
	}

}
