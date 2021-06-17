package it.uniroma3.siw.spring;

public interface EmailService {

	public void sendSimpleMessage(String to, String subject, String text);
}
