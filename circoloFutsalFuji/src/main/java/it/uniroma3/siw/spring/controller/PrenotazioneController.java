package it.uniroma3.siw.spring.controller;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.EmailService;
import it.uniroma3.siw.spring.controller.validator.PrenotazioneValidator;
import it.uniroma3.siw.spring.controller.validator.UtenteValidator;
import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.model.Utente;
import it.uniroma3.siw.spring.service.CampoService;
import it.uniroma3.siw.spring.service.PrenotazioneService;
import it.uniroma3.siw.spring.util.OraParser;

@Controller
public class PrenotazioneController {

	private static final Logger logger = LogManager.getLogger(PrenotazioneController.class);

	@Autowired
	private PrenotazioneService prenotazioneService;

	@Autowired
	private CampoService campoService;

	@Autowired
	private PrenotazioneValidator prenotazioneValidator;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UtenteValidator utenteValidator;

	@RequestMapping(value = "/prenota", method = RequestMethod.GET)
	public String prenotaCampo(@ModelAttribute("campo_id") Long campo_id, Model model) {
		logger.debug(campo_id);
		model.addAttribute("campo_id", campo_id);
		model.addAttribute("campo", campoService.campoPerId(campo_id));
		model.addAttribute("prenotazione", new Prenotazione());
		model.addAttribute("utente", new Utente());
		this.prenotazioneService.rimuoviPrenotazioniNonConfermate();
		return "prenotazione.html";
	}

	@RequestMapping(value = "/addPrenotazione", method = RequestMethod.POST)
	public String addPrenotazione(@ModelAttribute("utente") Utente utente, @ModelAttribute("campo_id") Long campo_id,
			@ModelAttribute("hinizio") String hinizio, @ModelAttribute("hfine") String hfine,
			@ModelAttribute("prenotazione") Prenotazione prenotazione, Model model, BindingResult bindingResult) {

		prenotazione.setOrarioInizio(OraParser.effettuaParse(hinizio, bindingResult));
        prenotazione.setOrarioFine(OraParser.effettuaParse(hfine, bindingResult));

		prenotazioneValidator.validate(prenotazione, bindingResult);
		utenteValidator.validaUtente(utente, bindingResult);

		if (!bindingResult.hasErrors()) {
			/*Rimuovile prima di cercare se ci sono conflitti con questa prenotazione*/
			this.prenotazioneService.rimuoviPrenotazioniNonConfermate();
			
			if (!prenotazioneService.alreadyExists((Long) campo_id, prenotazione)) {
				Campo campo = campoService.campoPerId(campo_id);
				prenotazione.setUtente(utente);
				prenotazione.setDataDiCreazione(LocalDateTime.now());
				campo.aggiungiPrenotazione(prenotazione);
				prenotazioneService.inserisci(prenotazione);

				String email = utente.getEmail().trim();
				String codice = prenotazione.getCodice();
				emailService.inviaCodicePerEmail(email, campo.getId(), prenotazione.getData(), hinizio, hfine, codice);

				model.addAttribute("campi", this.campoService.tutti());
				return "campi.html";
			} else {
				bindingResult.reject("prenotazione.duplicato");
			}
		}
		model.addAttribute("campo_id", campo_id);
		model.addAttribute("campo", campoService.campoPerId(campo_id));
		this.prenotazioneService.rimuoviPrenotazioniNonConfermate();
		return "prenotazione.html";
	}

	@RequestMapping(value = "/confermaPrenotazione/{codice}", method = RequestMethod.GET)
	public String confermaPrenotazione(Model model, @PathVariable("codice") String codiceConferma) {
		this.prenotazioneService.rimuoviPrenotazioniNonConfermate();
		Prenotazione prenotazione = this.prenotazioneService.prenotazionePerCodice(codiceConferma);
		if (prenotazione == null || prenotazione.isConfermata()) {
			model.addAttribute("prenotazione", prenotazione);
			return "erroreConferma.html";
		} else {
			prenotazione.setConfermata(true);
			return "index.html";
		}
	}
}
