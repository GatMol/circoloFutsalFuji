package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Campo;

@Component
public class CampoValidator implements Validator {

	private final Logger logger = LoggerFactory.getLogger(CampoValidator.class);
	
	public void validate(Object o, Errors errors) {
		Campo campo = (Campo) o;
		logger.debug("il campo nel validator:" + campo.toString());

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lunghezza", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "larghezza", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoCampo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoTerreno", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prezzo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "img1", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "img2", "required");

		if (!errors.hasFieldErrors("lunghezza")) {
			int lunghezza = campo.getLunghezza();
			if (lunghezza < 0)
				errors.rejectValue("lunghezza", "negativo");
			else if (lunghezza == 0)
				errors.rejectValue("lunghezza", "zero");
			else if (lunghezza < 25)
				errors.rejectValue("lunghezza", "limiteInf");
			else if (lunghezza > 42)
				errors.rejectValue("lunghezza", "limiteSup");
		}

		if (!errors.hasFieldErrors("larghezza")) {
			int larghezza = campo.getLarghezza();
			if (larghezza < 0)
				errors.rejectValue("larghezza", "negativo");
			else if (larghezza == 0)
				errors.rejectValue("larghezza", "zero");
			else if (larghezza < 15)
				errors.rejectValue("larghezza", "limiteInf");
			else if (larghezza > 25)
				errors.rejectValue("larghezza", "limiteSup");
		}

		if (!errors.hasFieldErrors("tipoCampo")) {
			String tipoCampo = campo.getTipoCampo().trim();
			if (!tipoCampo.equals("outdoor") && !tipoCampo.equals("Outdoor") && !tipoCampo.equals("indoor")
					&& !tipoCampo.equals("Indoor"))
				errors.rejectValue("tipoCampo", "invalid");
		}

		if (!errors.hasFieldErrors("prezzo")) {
			int prezzo = campo.getPrezzo();
			if (prezzo < 0)
				errors.rejectValue("prezzo", "negativo");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Campo.class.equals(clazz);
	}
}
