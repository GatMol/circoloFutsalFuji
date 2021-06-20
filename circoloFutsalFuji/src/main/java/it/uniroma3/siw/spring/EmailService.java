package it.uniroma3.siw.spring;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	private void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@futsalfuji.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	public void inviaCodicePerEmail(String email, Long idCampo, LocalDate data, 
			String hinizio, String hfine, String codice) {
		this.sendSimpleMessage(email, "Conferma prenotazione",
				"Conferma la prenotazione per la partita:\n"
				+ "Campo: " + idCampo + "\n"
				+ "Data: " + data + "\n"
				+ "Ora inizio: " + hinizio + "\n"
				+ "Ora fine: " + hfine + "\n"
				+ "Codice: http://localhost:8090/confermaPrenotazione/" + codice);
	}
}
