package it.uniroma3.siw.spring.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Campo;

@Component
public class CampoValidator implements Validator {

	public void validate(Object o, Errors errors) {
		Campo campo = (Campo) o;
		int lunghezza = campo.getLunghezza();
		int larghezza = campo.getLarghezza();
		String tipoCampo = campo.getTipoCampo().trim();
		String tipoTerreno = campo.getTipoTerreno().trim();
		int prezzo = campo.getPrezzo();
		String img1 = campo.getImg1();
		String img2 = campo.getImg2();
		
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
		
		if(tipoTerreno.isEmpty())
			errors.rejectValue("tipoTerreno", "required");
	
		if(prezzo < 0)
			errors.rejectValue("prezzo", "negativo");
		
		if(img1==null) {
			errors.rejectValue("img1", "required");
		}
		
		if(img2==null) {
			errors.rejectValue("img2", "required");
		}
	}
	
	@Override
    public boolean supports(Class<?> clazz) {
        return Campo.class.equals(clazz);
    }
}
