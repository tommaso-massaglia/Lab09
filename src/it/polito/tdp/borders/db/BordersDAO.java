package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT CCode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int ccode = rs.getInt("CCode");
				String stateabb = rs.getString("StateAbb");
				String statename = rs.getString("StateNme");
				Country c = new Country(ccode, stateabb, statename);
				result.add(c);
			}
			conn.close();

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno, int stato) {

		String sql = "SELECT state1no, state2no, YEAR FROM contiguity WHERE conttype = 1 AND YEAR<=? AND state1no = ?";
		List<Border> result = new ArrayList<Border>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			st.setInt(2, stato);
			ResultSet rs = st.executeQuery();
			

			while (rs.next()) {

				int state1no = rs.getInt("state1no");
				int state2no = rs.getInt("state2no");
				int year = rs.getInt("YEAR");
				Border b = new Border(state1no, state2no, year);
				result.add(b);
			}

			conn.close();

			return result;

		}

		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

	}
}
