package it.polito.tdp.meteo.model;

import java.util.*;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class RicorsioneNuova {
	
	private double matrice[][] = new double[15][3];
	private int count[] = new int[3];
	
	MeteoDAO meteoDao = new MeteoDAO();
	List<Rilevamento> rilevamenti;
	public void attivaRicorsione(int mese) {
		this.rilevamenti = meteoDao.getAllRilevamentiaMese(mese);
	}
	
	private void ricorsione(List<Rilevamento> parziale,List<Rilevamento> def, int best, int k) {
		
		//caso terminale
		if(parziale.size()==15) {
		//	int costoTmp = calcolaPunteggio(parziale); 
			//if(best > costoTmp ) {
			//	best = costoTmp;
				def = parziale;
			}
		//}else {
			
			for(int i=0;i<3;i++) {
				if(count[i]<=6) {
				//	for(int j=k;j<6;j++) {
						count[i]++;
						//parziale.add(elemento[j][i]);
						if(count[i]>3) {
							//ricorsione(..)
						}
					}	
				}
		//	}
			
			
			
	//	}
		
		return;
	}
}
