package it.polito.tdp.meteo.model;

import java.util.*;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
	MeteoDAO meteoDao = new MeteoDAO();

	public Model() {

	}

	// of course you can change the String output with what you think works best
	public List<Rilevamento> getUmiditaMedia(int mese) {
		return null;
	}
	
	public List<String> getUmiditaMediaMese(int mese) {
		return this.meteoDao.getMediaRilevamentiMese(mese);
	}
	
	// of course you can change the String output with what you think works best
	public String trovaSequenza(int mese) {
		return "TODO!";
	}
	

}
