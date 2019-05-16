import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class Asiakas {

	private String m_asiakas_ID;
	private String m_etunimi;
	private String m_sukunimi;
	private String m_postinumero;
	private String m_paikkakunta;
	private String m_lahiosoite;
	private String m_email;
	private String m_puh_nro;
	
	private String sql;
	private Yhteys yhteysTesti = new Yhteys();
	

	public String getM_asiakas_ID() {
		return m_asiakas_ID;
	}

	public void setM_asiakas_ID(String m_asiakas_ID) {
		this.m_asiakas_ID = m_asiakas_ID;
	}

	public String getM_etunimi() {
		return m_etunimi;
	}

	public void setM_etunimi(String m_etunimi) {
		this.m_etunimi = m_etunimi;
	}

	public String getM_sukunimi() {
		return m_sukunimi;
	}

	public void setM_sukunimi(String m_sukunimi) {
		this.m_sukunimi = m_sukunimi;
	}

	public String getM_postinumero() {
		return m_postinumero;
	}

	public void setM_postinumero(String m_postinumero) {
		this.m_postinumero = m_postinumero;
	}

	public String getM_paikkakunta() {
		return m_paikkakunta;
	}

	public void setM_paikkakunta(String m_paikkakunta) {
		this.m_paikkakunta = m_paikkakunta;
	}

	public String getM_lahiosoite() {
		return m_lahiosoite;
	}

	public void setM_lahiosoite(String m_lahiosoite) {
		this.m_lahiosoite = m_lahiosoite;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_puh_nro() {
		return m_puh_nro;
	}

	public void setM_puh_nro(String m_puh_nro) {
		this.m_puh_nro = m_puh_nro;
	}

	/**
	 * Tämä metodi palauttaa listan kaikista asiakkaista
	 * @return Lista missä on toimipiste_ID + toimipisteen nimi
	 */
	public List getLista() {
		List holder = new ArrayList();
		sql = "SELECT asiakas_ID, etunimi, sukunimi FROM ASIAKKAAT;";
		ResultSet tulokset = yhteysTesti.TuoLista(sql);
		
		try {
			while(tulokset.next()) {
				holder.add(tulokset.getString("asiakas_ID") + " " + tulokset.getString("etunimi")+ " " + tulokset.getString("sukunimi"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return holder;
	}
	
	/**
	 * Tämä metodi lisää uuden asiakkaan halutuilla parametreilla
	 * @param p_etunimi Asiakkaan etunimi
	 * @param p_sukunimi Asiakkaan sukunimi
	 * @param p_postinumero Asiakkaan postinumero
	 * @param p_paikkakunta Asiakkaan paikkakunta
	 * @param p_lahiosoite Asiakkaan lähiosoite
	 * @param p_email Asiakkaan email
	 * @param p_puh_nro Asiakkaan puhelinnumero
	 */
	public void lisaa(String p_etunimi, String p_sukunimi, String p_postinumero, String p_paikkakunta, String p_lahiosoite, String p_email, String p_puh_nro ) {
		sql = "INSERT INTO ASIAKKAAT(etunimi, sukunimi, postinumero, paikkakunta, lahiosoite, email, puh_nro) VALUES('" + p_etunimi + "', '" + p_sukunimi + "', '" + p_postinumero + "', '" + p_paikkakunta + "', '" + p_lahiosoite + "', '" + p_email + "', '" + p_puh_nro + "');";
		yhteysTesti.Paivita(sql);
		}
	
	/**
	 * Tämä metodi poistaa halutun rivin
	 * @param p_id Poistettavan rivin asiakas_ID
	 */
	public void poista(String p_id ) {
		sql = "DELETE FROM ASIAKKAAT WHERE asiakas_ID =" + p_id + ";";
		yhteysTesti.Paivita(sql);
		}

	/**
	 * Tämä metodi päivittää halutun asiakkaan tiedot
	 * @param p_etunimi Uusi etunimi
	 * @param p_sukunimi Uusi sukunimi
	 * @param p_postinumero Uusi postinumero
	 * @param p_paikkakunta Uusi paikkakunta
	 * @param p_lahiosoite Uusi lähiosoite
	 * @param p_email Uusi email
	 * @param p_puh_nro Uusi puh_nro
	 * @param p_id Halutun asikkaan ID
	 */
	public void paivita(String p_etunimi, String p_sukunimi, String p_postinumero, String p_paikkakunta, String p_lahiosoite, String p_email, String p_puh_nro, String p_id) {
		sql = "UPDATE ASIAKKAAT "
				+ "SET etunimi ='" + p_etunimi + "', sukunimi = '" + p_sukunimi + "', postinumero = '" + p_postinumero + "', paikkakunta = '" + p_paikkakunta + "', lahiosoite = '" + p_lahiosoite + "', email = '" + p_email + "', puh_nro = '" + p_puh_nro + "' WHERE asiakas_ID ='" + p_id + "';"; 
		yhteysTesti.Paivita(sql);
		}
	
	/**
	 * Tämä metodi päivittää instanssiin halutun sarakkeen tiedot
	 * @param p_id Halutun toimipisteen toimipiste_ID
	 */
	public void otaTiedot(String p_id) {
		sql = "SELECT * FROM ASIAKKAAT WHERE asiakas_ID =" + p_id + ";";
		ResultSet tulokset = yhteysTesti.TuoLista(sql);
		
		try {
			while(tulokset.next()) {
				m_asiakas_ID = tulokset.getString("asiakas_ID");
				m_etunimi = tulokset.getString("etunimi");
				m_sukunimi = tulokset.getString("sukunimi");
				m_postinumero = tulokset.getString("postinumero");
				m_paikkakunta = tulokset.getString("paikkakunta");
				m_lahiosoite = tulokset.getString("lahiosoite");
				m_email = tulokset.getString("email");
				m_puh_nro = tulokset.getString("puh_nro");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
