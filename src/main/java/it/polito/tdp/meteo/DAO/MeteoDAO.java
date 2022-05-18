package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiaMese(int mese) {

		 String sql = "SELECT localita, data, umidita "
		 		+ "FROM situazione "
		 		+ "WHERE Month(data) = ? "
		 		+ "ORDER BY data ASC";
			List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();
			
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDouble(1, mese);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<String> getMediaRilevamentiMese(int mese) {

		 String sql = "SELECT Localita, AVG(Umidita) as u "+
				 "FROM situazione "+
				"where Month(data)=? "+
				 "GROUP BY Localita";
		 List<String> rilevamenti = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, mese);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				double d = rs.getDouble("u");
				String s = new String(rs.getString("Localita"));
				s+= ": "+d;
				rilevamenti.add(s);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
