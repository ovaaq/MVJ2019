import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Toimipiste {
	
	private String m_toimipiste_ID;
	private String m_nimi;
	private String m_puh_nro;
	private String m_lahiosoite;
	private String m_postinumero;
	private String m_paikkakunta;
	private String m_email;
	
	private String sql;
	private Yhteys yhteysTesti = new Yhteys();
	
	public String getM_toimipiste_ID() {
		return m_toimipiste_ID;
	}

	public void setM_toimipiste_ID(String m_toimipiste_ID) {
		this.m_toimipiste_ID = m_toimipiste_ID;
	}

	public String getM_nimi() {
		return m_nimi;
	}

	public void setM_nimi(String m_nimi) {
		this.m_nimi = m_nimi;
	}

	public String getM_puh_nro() {
		return m_puh_nro;
	}

	public void setM_puh_nro(String m_puh_nro) {
		this.m_puh_nro = m_puh_nro;
	}

	public String getM_lahiosoite() {
		return m_lahiosoite;
	}

	public void setM_lahiosoite(String m_lahiosoite) {
		this.m_lahiosoite = m_lahiosoite;
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

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	/**
	 * Tämä metodi palauttaa listan kaikista toimipisteistä
	 * @return Lista missä on toimipiste_ID + toimipisteen nimi
	 */
	public List getLista() {
		List holder = new ArrayList();
		sql = "SELECT toimipiste_ID, nimi FROM TOIMIPISTEET;";
		ResultSet tulokset = yhteysTesti.TuoLista(sql);
		
		try {
			while(tulokset.next()) {
				holder.add(tulokset.getString("toimipiste_ID") + " " + tulokset.getString("nimi"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return holder;
	}
	
	/**
	 * Tämä metodi lisää uuden toimipisteen halutuilla parametreilla
	 * @param p_nimi Toimipisteen nimi
	 * @param p_puh_num Toimipisteen puh_nro
	 * @param p_lahiosoite Toimipisteen lähiosoite
	 * @param p_postitoimipaikka Toimipisteen postitoimipaikka
	 * @param p_paikkakunta Toimipisteen paikkakunta
	 * @param p_email Toimipisteen email
	 */
	public void lisaa(String p_nimi, String p_puh_num, String p_lahiosoite, String p_postitoimipaikka, String p_paikkakunta, String p_email ) {
		sql = "INSERT INTO TOIMIPISTEET(nimi, puh_nro, lahiosoite, postinumero, paikkakunta, email) VALUES('" + p_nimi + "', '" + p_puh_num + "', '" + p_lahiosoite + "', '" + p_postitoimipaikka + "', '" + p_paikkakunta + "', '" + p_email + "');";
		yhteysTesti.Paivita(sql);
		}
	
	/**
	 * Tämä metodi poistaa halutun rivin
	 * @param p_id Poistettavan rivin toimipiste_ID
	 */
	public void poista(String p_id ) {
		sql = "DELETE FROM TOIMIPISTEET WHERE toimipiste_ID =" + p_id + ";";
		yhteysTesti.Paivita(sql);
		}

	/**
	 * Tämä metodi päivittää tietokantaan kaikki tiedot halutulle toimipiste_ID:lle
	 * @param p_nimi Toimipisteen nimi
	 * @param p_puh_num Toimipisteen puh_nro
	 * @param p_lahiosoite Toimipisteen lähiosoite
	 * @param p_postitoimipaikka Toimipisteen postitoimipaikka
	 * @param p_paikkakunta Toimipisteen paikkakunta
	 * @param p_email Toimipisteen email
	 * @param p_id Toimipisteen toimipiste_ID
	 */
	public void paivita(String p_nimi, String p_puh_num, String p_lahiosoite, String p_postitoimipaikka, String p_paikkakunta, String p_email, String p_id ) {
		sql = "UPDATE TOIMIPISTEET "
				+ "SET nimi ='" + p_nimi + "', puh_nro = '" + p_puh_num + "', lahiosoite = '" + p_lahiosoite + "', postinumero = '" + p_postitoimipaikka + "', paikkakunta = '" + p_paikkakunta + "', email = '" + p_email + "' WHERE toimipiste_ID ='" + p_id + "';"; 
		yhteysTesti.Paivita(sql);
		}
	
	/**
	 * Tämä metodi päivittää instanssiin halutun sarakkeen tiedot
	 * @param p_id Halutun toimipisteen toimipiste_ID
	 */
	public void otaTiedot(String p_id) {
		sql = "SELECT * FROM TOIMIPISTEET WHERE toimipiste_ID =" + p_id + ";";
		ResultSet tulokset = yhteysTesti.TuoLista(sql);
		
		try {
			while(tulokset.next()) {
				m_toimipiste_ID = tulokset.getString("toimipiste_ID");
				m_nimi = tulokset.getString("nimi");
				m_puh_nro = tulokset.getString("puh_nro");
				m_lahiosoite = tulokset.getString("lahiosoite");
				m_postinumero = tulokset.getString("postinumero");
				m_paikkakunta = tulokset.getString("paikkakunta");
				m_email = tulokset.getString("email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
