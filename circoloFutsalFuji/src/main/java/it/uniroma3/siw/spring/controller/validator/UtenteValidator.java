package it.uniroma3.siw.spring.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.spring.model.Utente;
import it.uniroma3.siw.spring.util.EmailValidator;

@Component
public class UtenteValidator {

	public void validaUtente(Utente u, BindingResult errors) {

		Utente utente = (Utente) u;
		if(utente.getNome().isEmpty())
			errors.reject("nome.vuoto");

		if(utente.getCognome().isEmpty())
			errors.reject("cognome.vuoto");

		if(utente.getEmail().isEmpty()) {
			errors.reject("email.vuoto");
		}else {
			if(!EmailValidator.isValidEmailAddress(utente.getEmail()))
				errors.reject("email.nonvalida");
		}

		if(utente.getTelefono().isEmpty()) {
			errors.reject("telefono.vuoto");
		}else {
			if(utente.getTelefono().length()!=10) {
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
}
