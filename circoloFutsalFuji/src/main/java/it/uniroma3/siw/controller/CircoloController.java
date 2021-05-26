package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.service.CampoService;

@Controller
public class CircoloController {
	
	@Autowired
	private CampoService campoService;
	
	@RequestMapping(value = "/campo/{id}" ,method = RequestMethod.GET)
	public String getCampo(@PathVariable Long id, Model model) {
		model.addAttribute("campo", this.campoService.campoPerId(id));
		return "campo.html";
	}

}
