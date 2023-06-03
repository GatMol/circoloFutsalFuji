package it.uniroma3.siw.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.repository.CredentialsRepository;

@Service
public class CredentialsService {
	
	@Autowired
	private CredentialsRepository credentialsRepository;

	public Credentials getCredentials(String username) {
		return credentialsRepository.findByUsername(username);
	}

}
