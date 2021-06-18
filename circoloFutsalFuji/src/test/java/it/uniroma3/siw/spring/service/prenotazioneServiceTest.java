package it.uniroma3.siw.spring.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.service.PrenotazioneService;

@SpringBootTest
public class prenotazioneServiceTest {
	
	private static final Logger logger = LogManager.getLogger(prenotazioneServiceTest.class);
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@BeforeEach
	public void rimuoviPrenotazioniNelDb() {
		this.prenotazioneService.rimuoviTutti();
	}

	@Test
	public void rimozioneCollezioniNonConfermateTest() {
		LocalDate dataOdierna = LocalDate.now();
		LocalDateTime unGiornoPrima = LocalDateTime.now().minusDays(1);
		LocalDateTime ventiMinutiPrima = LocalDateTime.now().minusMinutes(20);
		LocalDateTime cinqueMinutiPrima = LocalDateTime.now().minusMinutes(5);
		
		LocalTime oraInizioP1 = LocalTime.now();
		LocalTime oraFineP1 = oraInizioP1.plusHours(1);
		
		LocalTime oraInizioP2 = oraFineP1.plusHours(1);
		LocalTime oraFineP2 = oraInizioP2.plusHours(1);
		
		LocalTime oraInizioP3 = oraFineP2.plusHours(1);
		LocalTime oraFineP3 = oraInizioP3.plusHours(1);

		LocalTime oraInizioP4 = oraFineP3.plusHours(1);
		LocalTime oraFineP4 = oraInizioP4.plusHours(1);
		
		Prenotazione p1 = new Prenotazione(dataOdierna, oraInizioP1, oraFineP1, unGiornoPrima);
		Prenotazione p2 = new Prenotazione(dataOdierna, oraInizioP2, oraFineP2, ventiMinutiPrima);
		Prenotazione p3 = new Prenotazione(dataOdierna, oraInizioP3, oraFineP3, cinqueMinutiPrima);
		
		Prenotazione p4Confermata = new Prenotazione(dataOdierna, oraInizioP4, oraFineP4, unGiornoPrima);
		p4Confermata.setConfermata(true);
		
		this.prenotazioneService.inserisci(p1);
		this.prenotazioneService.inserisci(p2);
		this.prenotazioneService.inserisci(p3);
		this.prenotazioneService.inserisci(p4Confermata);
		this.prenotazioneService.rimuoviPrenotazioniNonConfermate();
		
		List<Prenotazione> prenotazioniNelDb = this.prenotazioneService.tutteLePrenotazioni(); 
		logger.debug("Prenotazione rimasta nel db:\n" + prenotazioniNelDb.toString());
		assertEquals(2,prenotazioniNelDb.size());
	}
	
}
