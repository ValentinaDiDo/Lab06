package it.polito.tdp.meteo.model;
import java.time.LocalDate;
import java.util.*;

import it.polito.tdp.meteo.DAO.MeteoDAO;
public class Ricorsione {

	MeteoDAO meteoDao = new MeteoDAO();
	List<Citta> definitivaCitta = new ArrayList<Citta>();
	List<Rilevamento> definitiva = new ArrayList<>();
	List<Rilevamento> rilevamenti;
	double costoPrecedente = 0.0;
	
	public void attivaRicorsione(int mese) {
		this.rilevamenti = meteoDao.getAllRilevamentiaMese(mese);
		//ho la lista di tutti i rilevamenti su cui devo scorrere
		List<Rilevamento> parziale = new ArrayList<Rilevamento>();
		List<Date> date = new ArrayList<Date>();
		ottimizzazioneRilevamenti(parziale, 0, 0.0, date);
		
	}
	/*public void ottimizzazioneRilevamenti(List<Citta> parziale, int livello, double costo, List<Date> date) {
		
		//caso terminale
		if(livello == 15 && haVisitatoTutteCitta(parziale) && calcolaCosto(parziale, date) <= costoPrecedente) {
			definitiva.clear();
			definitivaCitta.addAll(parziale);
			costoPrecedente = calcolaCosto(parziale, date);
			return;
		//caso normale
		}else { 
			
			for(Rilevamento r : rilevamenti) {
				//ora inizio dal primo elemento in poi
				//devo aggiungere la data e la citta
			}
			
		}
		
	}*/
	
	
public void ottimizzazioneRilevamenti(List<Rilevamento> parziale, int livello, double costo, List<Date> date) {
		
		//caso terminale
		if(livello == 15 && haVisitatoTutteCitta(parziale)) {
			double costoParziale = calcolaCosto(parziale, date);
			
			if(costoPrecedente == 0.0) {
				//questo è il caso della prima soluzione
				definitiva.addAll(parziale);
				costoPrecedente = costoParziale;
				return;
				
			}else if (costoParziale <= costoPrecedente) {
				definitiva.clear();
				definitiva.addAll(parziale);
				costoPrecedente = costoParziale;
				return;
			
			}
		//caso normale
		}else { 
			int cont = 0;
			for(Rilevamento r : rilevamenti) {
				//ora inizio dal primo elemento in poi
				//devo aggiungere la data e la citta
				if(!date.contains(r.getData())) { 	//entro qua appena trovo una data successiva all'ultima registrata
					//controllo città:
			
					if(parziale.size()>=3) {
						Rilevamento terzultimo = rilevamenti.get(cont-2);
						Rilevamento penultimo = rilevamenti.get(cont-1);
						Rilevamento ultimo = r;
						
						///////
						
						
					}if(parziale.size()==2) {
						Rilevamento penultimo = rilevamenti.get(cont-1);
						Rilevamento ultimo = r;
					}if(parziale.size()==1) {
						Rilevamento ultimo = r;
					}
					
				}
				cont ++ ; //tengo memoria dell'indice in cui mi trovo
			}
			
		}
		
	}

	private Rilevamento scegliProssima(List<Rilevamento> parziale) {
		Rilevamento prossimo = null;
		
		if(parziale.size()<3) { //per forza devo scegliere la stessa città che ho messo per prima
			
		}else {
			
		}
		
		return prossimo;
	}
	
	private double calcolaCosto(List<Rilevamento> parziale, List<Date> date) {
		
		//CALCOLO COSTO TOTALE SOLUZIONE PARZIALE
		double costo = 0.0;
		
		for(int i = 0; i<parziale.size(); i++) {
			
			Rilevamento penultimo = null;
			if(parziale.size()>=2)
				penultimo = parziale.get(i-1);
			 
			Rilevamento ultimo = parziale.get(i);
			
			String penultimaCitta = penultimo.getLocalita();
			String ultimaCitta = ultimo.getLocalita();
			
			if(!ultimaCitta.equals(penultimaCitta))
				costo+=100; //aumento costo perché è stata cambiata città
			
 			costo+= ultimo.getUmidita();
		}
		
		return costo;
	}
	
	
	public boolean haVisitatoTutteCitta(List<Rilevamento> parziale) {
		int contT = 0;
		int contM = 0;
		int contG = 0;
		
		/*for(Citta c : parziale) {
			if(c.getNome().compareTo("Torino") == 0)
				contT ++;
			if(c.getNome().compareTo("Genova") == 0)
				contG ++;
			if(c.getNome().compareTo("Milano") == 0)
				contM ++;
		}*/
		
		for(Rilevamento r : parziale) {
			if(r.getLocalita().compareTo("Torino")==0)
				contT ++;
			if(r.getLocalita().compareTo("Genova") == 0)
				contG ++;
			if(r.getLocalita().compareTo("Milano") == 0)
				contM ++;
		}
		if(contT == 0 || contG == 0 || contM == 0)
			return false;
		return true; //ritorna true se ha visitato almeno una volta tutte le città
	}
	
	
}
