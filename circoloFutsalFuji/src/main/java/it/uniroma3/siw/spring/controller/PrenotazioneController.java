package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.model.Utente;
import it.uniroma3.siw.spring.service.PrenotazioneService;

@Controller
public class PrenotazioneController {
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Autowired
	private PrenotazioneValidator prenotazioneValidator;
	
	@RequestMapping(value="/addPrenotazione", method = RequestMethod.POST)
	public String addPrenotazione(@ModelAttribute("prenotazione") Prenotazione prenotazione,
								  @ModelAttribute("utente") Utente utente, 
								  @ModelAttribute("campo") Campo campo,
										Model model, BindingResult bindingResult) {
		prenotazione.setUtente(utente);
		prenotazioneValidator.validate(prenotazione, bindingResult);
		if(!bindingResult.hasErrors()) {
			campo.aggiungiPrenotazione(prenotazione);
			prenotazioneService.inserisci(prenotazione);
			return "campi.html";
		}
		return "prenotazione.html";
	}
	
}
