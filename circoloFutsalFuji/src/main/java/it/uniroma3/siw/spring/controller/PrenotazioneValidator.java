package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.service.PrenotazioneService;

@Component
public class PrenotazioneValidator implements Validator{
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "data", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orarioInizio", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orarioFine", "required");
		
		if(!errors.hasErrors()) {
			if(this.prenotazioneService.alreadyExists((Prenotazione) o))
				errors.reject("duplicato");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Prenotazione.class.equals(clazz);
	}

}
