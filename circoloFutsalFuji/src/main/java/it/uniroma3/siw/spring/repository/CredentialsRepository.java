package it.uniroma3.siw.spring.repository;


import it.uniroma3.siw.spring.model.Credentials;
import org.springframework.data.repository.CrudRepository;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	
	public Credentials findByUsername(String username);

}
