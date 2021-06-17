package it.uniroma3.siw.spring.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Campo;

@Component
public class CampoValidator implements Validator {

	public void validate(Object o, Errors errors) {
		Campo campo = (Campo) o;
		int lunghezza = campo.getLunghezza();
		int larghezza = campo.getLarghezza();
		String tipoCampo = campo.getTipoCampo().trim();
		int prezzo = campo.getPrezzo();

		if(lunghezza < 0)
			errors.rejectValue("lunghezza", "negativo");
		else if(lunghezza == 0)
			errors.rejectValue("lunghezza", "zero");
		else if(lunghezza < 25)
			errors.rejectValue("lunghezza", "limiteInf");
		else if(lunghezza > 42)
			errors.rejectValue("lunghezza", "limiteSup");
		
		if(larghezza < 0)
			errors.rejectValue("larghezza", "negativo");
		else if(larghezza == 0)
			errors.rejectValue("larghezza", "zero");
		else if(larghezza < 15)
			errors.rejectValue("larghezza", "limiteInf");
		else if(larghezza > 25)
			errors.rejectValue("larghezza", "limiteSup");
		
		if(tipoCampo.isEmpty())
			errors.rejectValue("tipoCampo", "required");
		else if(!tipoCampo.equals("outdoor") && !tipoCampo.equals("Outdoor") 
				&& !tipoCampo.equals("indoor") && !tipoCampo.equals("Indoor")) {
			errors.rejectValue("tipoCampo", "invalid");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoTerreno", "required");
			
		if(prezzo < 0)
			errors.rejectValue("prezzo", "negativo");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "img1", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "img2", "required");
	}
	
	@Override
    public boolean supports(Class<?> clazz) {
        return Campo.class.equals(clazz);
    }
}
