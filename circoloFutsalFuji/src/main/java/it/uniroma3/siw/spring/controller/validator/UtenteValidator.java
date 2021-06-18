package it.uniroma3.siw.spring.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Utente;
import it.uniroma3.siw.spring.util.EmailValidator;

@Component
public class UtenteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Utente.class.equals(clazz);
	}

	@Override
	public void validate(Object u, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "required");
		
		Utente utente = (Utente) u;
		
		if(!EmailValidator.isValidEmailAddress(utente.getEmail()))
			errors.reject("email.nonvalida");
		
		if(utente.getTelefono().length()!=9) {
			errors.reject("telefono.nonvalido");
		}else {
			try {
				Long.parseLong(utente.getTelefono());
			}catch(NumberFormatException e) {
				errors.reject("telefono.nonnumerico");
			}
		}
	}

}
