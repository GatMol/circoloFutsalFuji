package it.uniroma3.siw.spring.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.controller.validator.CampoValidator;
import it.uniroma3.siw.spring.middleware.FileUploadUtil;
import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.service.CampoService;
import it.uniroma3.siw.spring.service.PrenotazioneService;

@Controller
public class GestoreController {

	@Autowired
	private CampoService campoService;

	@Autowired
	private CampoValidator campoValidator;

	@Autowired
	private PrenotazioneService prenotazioneService;
	
	private static final Logger logger = LogManager.getLogger(GestoreController.class);
	
	@RequestMapping(value = "/gestore", method = RequestMethod.GET)
	public String getGestore(Model model) {
		return "gestore.html";
	}
	
	@RequestMapping(value = "/admin/aggiungiCampo", method = RequestMethod.GET)
	public String newCampo(Model model) {
		model.addAttribute("campo", new Campo());
		return "admin/aggiungiCampoForm.html";
	}

	@RequestMapping(value = "/admin/aggiungiCampo", method = RequestMethod.POST)
	public String salvaCampo(Model model, @ModelAttribute("campo") Campo campo, 
			@RequestParam("image1") MultipartFile multipartFile1, @RequestParam("image2") MultipartFile multipartFile2,
			BindingResult campoBindingResult){
		logger.debug("Ecco il contenuto del model" + model);

		String fileName1 = StringUtils.cleanPath(multipartFile1.getOriginalFilename());
		String fileName2 = StringUtils.cleanPath(multipartFile2.getOriginalFilename());
		
		campo.setImg1(fileName1);
		campo.setImg2(fileName2);

		logger.debug("il campo nel model " + campo.toString());
		campoValidator.validate(campo, campoBindingResult);

		if (!campoBindingResult.hasErrors()) {

			Campo savedCampo = this.campoService.inserisci(campo);
			String uploadDir = "campo-photos/" + savedCampo.getId();

			try {
				FileUploadUtil.saveFile(uploadDir, fileName1, multipartFile1);
				FileUploadUtil.saveFile(uploadDir, fileName2, multipartFile2);
			} catch (IOException e) {
				campoBindingResult.rejectValue("img1", "required");
				campoBindingResult.rejectValue("img2", "required");
				return "admin/aggiungiCampoForm.html";
			}
			model.addAttribute("campi", this.campoService.tutti());
			return "campi.html";
		}

		else {
			return "admin/aggiungiCampoForm.html";
		}
	}
	
	@RequestMapping(value = "/admin/rimuoviCampo", method = RequestMethod.GET)
	public String showCampiAdmin(Model model) {
		model.addAttribute("campi", this.campoService.tutti());
		return "campi.html";
	}
	
	@RequestMapping(value = "/admin/rimuoviCampo/{id}", method = RequestMethod.GET)
	public String rimuoviCampi(Model model, @PathVariable("id") Long id) {
		this.campoService.rimuoviCampoPerId(id);
		model.addAttribute("campi", this.campoService.tutti());
		return "campi.html";
	}
	
	@RequestMapping(value = "/admin/prenotazioni", method = RequestMethod.GET)
	public String getPrenotazioni(Model model) {
		this.prenotazioneService.rimuoviPrenotazioniNonConfermate();
		model.addAttribute("prenotazioni", this.prenotazioneService.tutteLePrenotazioni());
		return "admin/prenotazioni.html";
	}
}
