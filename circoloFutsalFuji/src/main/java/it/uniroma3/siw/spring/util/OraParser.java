package it.uniroma3.siw.spring.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.validation.BindingResult;

import it.uniroma3.siw.spring.model.Prenotazione;

public class OraParser {

    public static LocalTime effettuaParse(String ora, BindingResult bindingResult) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            return LocalTime.parse(ora, formatter);
        }catch(DateTimeParseException e){
            bindingResult.reject("oravuota");
            return null;
        }
    }

}
