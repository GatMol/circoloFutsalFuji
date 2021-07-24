package it.uniroma3.siw.spring.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.model.Prenotazione;

@SpringBootTest
public class prenotazioneServiceTest {

	private static final Logger logger = LogManager.getLogger(prenotazioneServiceTest.class);

	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@BeforeEach
	public void rimuoviPrenotazioniNelDb() {
		this.prenotazioneService.rimuoviTutti();
	}

	@Autowired
	private CampoService campoService;

	private Prenotazione p1;
	private Prenotazione p2;

	private Campo campo;

	private LocalDate dataOdierna;

	private LocalTime oraFineP2;

	private LocalDateTime unGiornoPrima;

	@BeforeEach
	public void setUp() {
		this.prenotazioneService.rimuoviTutti();
		this.campoService.rimuoviTutti();

		unGiornoPrima = LocalDateTime.now().minusDays(1);
		LocalDateTime ventiMinutiPrima = LocalDateTime.now().minusMinutes(20);
		
		dataOdierna = LocalDate.now();

		LocalTime oraInizioP1 = LocalTime.now();
		LocalTime oraFineP1 = oraInizioP1.plusHours(1);

		LocalTime oraInizioP2 = oraFineP1.plusHours(1);
		oraFineP2 = oraInizioP2.plusHours(1);

		p1 = new Prenotazione(dataOdierna, oraInizioP1, oraFineP1, unGiornoPrima);
		p2 = new Prenotazione(dataOdierna, oraInizioP2, oraFineP2, ventiMinutiPrima);

		campo = new Campo();
		campo.setId((long) 1);
		campo.setLarghezza(122);
		campo.setLunghezza(111);
		campo.setImg1("pa");
		campo.setImg2("ptf");
		campo.setPrezzo(5);
		campo.setTipoCampo("da");
		campo.setTipoTerreno("da");
		campo.aggiungiPrenotazione(p1);
	}

	@Test
	public void rimozioneCollezioniNonConfermateTest() {
		
		LocalTime oraInizioP3 = oraFineP2.plusHours(1);
		LocalTime oraFineP3 = oraInizioP3.plusHours(1);
		LocalTime oraInizioP4 = oraFineP3.plusHours(1);
		LocalTime oraFineP4 = oraInizioP4.plusHours(1);
		LocalDateTime cinqueMinutiPrima = LocalDateTime.now().minusMinutes(5);
		Prenotazione p3 = new Prenotazione(dataOdierna, oraInizioP3, oraFineP3, cinqueMinutiPrima);
		
		Prenotazione p4Confermata = new Prenotazione(dataOdierna, oraInizioP4, oraFineP4, unGiornoPrima);
		p3 = new Prenotazione(dataOdierna, oraInizioP3, oraFineP3, cinqueMinutiPrima);
		p4Confermata = new Prenotazione(dataOdierna, oraInizioP4, oraFineP4, unGiornoPrima);
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

	@Test
	public void inserimentoPrenotazioneCampoDiverso() {
		this.prenotazioneService.inserisci(p1);
		this.campoService.inserisci(campo);
		assertFalse(this.prenotazioneService.alreadyExists((long) 2, p2));
	}

	@Test
	public void inserimentoPrenotazioneCampoUgualeConDataDiversa() {
		this.prenotazioneService.inserisci(p1);
		this.campoService.inserisci(campo);
		p2.setData(LocalDate.of(2021, 11, 12));
		assertFalse(this.prenotazioneService.alreadyExists((long) 1, p2));
	}

	@Test
	public void inserimentoPrenotazioneCampoUgualeConDataUgualeOrarioDiverso() {
		p1.setOrarioInizio(LocalTime.of(18, 22));
		p1.setOrarioFine(LocalTime.of(19, 22));
		this.prenotazioneService.inserisci(p1);
		this.campoService.inserisci(campo);
		p2.setOrarioInizio(LocalTime.of(21, 22));
		p2.setOrarioFine(LocalTime.of(22, 22));
		assertFalse(this.prenotazioneService.alreadyExists((long) 1, p2));
	}

	@Test
	public void inserimentoPrenotazioneCampoUgualeConDataUgualeOrarioUguale() {
		p1.setOrarioInizio(LocalTime.of(21, 22));
		p1.setOrarioFine(LocalTime.of(22, 22));
		this.prenotazioneService.inserisci(p1);
		this.campoService.inserisci(campo);
		p2.setOrarioInizio(LocalTime.of(21, 22));
		p2.setOrarioFine(LocalTime.of(22, 22));
		assertTrue(this.prenotazioneService.alreadyExists((long) 1, p2));
	}

	@Test
	public void inserimentoPrenotazioneCampoUgualeConDataUgualeOrarioCollidente() {
		p1.setOrarioInizio(LocalTime.of(21, 30));
		p1.setOrarioFine(LocalTime.of(22, 30));
		this.prenotazioneService.inserisci(p1);
		this.campoService.inserisci(campo);
		p2.setOrarioInizio(LocalTime.of(21, 00));
		p2.setOrarioFine(LocalTime.of(22, 00));
		assertTrue(this.prenotazioneService.alreadyExists((long) 1, p2));
	}

	@Test
	public void inserimentoPrenotazioneCampoUgualeConDataUgualeOrarioCollidenteDiverso() {
		p1.setOrarioInizio(LocalTime.of(21, 00));
		p1.setOrarioFine(LocalTime.of(22, 00));
		this.prenotazioneService.inserisci(p1);
		this.campoService.inserisci(campo);
		p2.setOrarioInizio(LocalTime.of(21, 30));
		p2.setOrarioFine(LocalTime.of(22, 30));
		assertTrue(this.prenotazioneService.alreadyExists((long) 1, p2));
	}

	@Test
	public void inserimentoPrenotazioneCampoUgualeConDataUgualeInizio() {
		p1.setOrarioInizio(LocalTime.of(21, 00));
		p1.setOrarioFine(LocalTime.of(22, 00));
		this.prenotazioneService.inserisci(p1);
		this.campoService.inserisci(campo);
		p2.setOrarioInizio(LocalTime.of(22, 00));
		p2.setOrarioFine(LocalTime.of(23, 00));
		assertFalse(this.prenotazioneService.alreadyExists((long) 1, p2));
	}

	@Test
	public void inserimentoPrenotazioneCampoUgualeConDataUgualeFineCoincidenteConInizio() {
		p1.setOrarioInizio(LocalTime.of(21, 00));
		p1.setOrarioFine(LocalTime.of(22, 00));
		this.prenotazioneService.inserisci(p1);
		this.campoService.inserisci(campo);
		p2.setOrarioInizio(LocalTime.of(20, 00));
		p2.setOrarioFine(LocalTime.of(21, 00));
		assertFalse(this.prenotazioneService.alreadyExists((long) 1, p2));
	}

}