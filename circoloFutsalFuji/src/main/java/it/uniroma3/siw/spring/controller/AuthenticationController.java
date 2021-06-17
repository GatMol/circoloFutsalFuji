package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.service.CredentialsService;

@Controller
public class AuthenticationController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CredentialsService credentialsService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String mostraLogin(Model model) {
		logger.debug("qui ci arrivo");
		return "loginForm.html";
	}
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String effettuaLogin(Model model) {
		logger.debug("qui ci arrivo");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if(credentials.getRole().equals(Credentials.ADMIN_ROLE))
			return "admin/gestore.html";
		return "index.html";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "index";
	}
	
}
