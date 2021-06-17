package it.uniroma3.siw.spring.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.EmailService;
import it.uniroma3.siw.spring.service.CampoService;

@Controller
public class CircoloController {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private CampoService campoService;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "/campi", method = RequestMethod.GET)
	public String getCampi(Model model) {
		model.addAttribute("campi", this.campoService.tutti());
		logger.debug("Caricato i campi, creo sender");
		logger.debug("Caricato i campi, Mando email");
		emailService.sendSimpleMessage("davidegatto99@gmail.com", "Prenotazione", "Eccoti l'email");
		logger.debug("Email inviata");
		return "campi.html";
	}
	
	@RequestMapping(value = "/campo/{id}", method = RequestMethod.GET)
	public String getCampo(@PathVariable Long id, Model model) {
		model.addAttribute("campo", this.campoService.campoPerId(id));
		return "campo.html";
	}
}