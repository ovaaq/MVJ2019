import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class PalveluJaMokki {
	
	private String m_mp_id;
	private String m_kapasiteetti;
	private String m_pinta_ala;
	private String m_kuvaus;
	private String m_kategoria;
	private String m_hinta;
	private String m_nimi;
	private String m_alv;
	private String m_toimipiste_ID;
	
	private String sql;
	private Yhteys yhteysTesti = new Yhteys();
	
	
	public String getM_mp_id() {
		return m_mp_id;
	}
	public void setM_mp_id(String m_mp_id) {
		this.m_mp_id = m_mp_id;
	}
	public String getM_kapasiteetti() {
		return m_kapasiteetti;
	}
	public void setM_kapasiteetti(String m_kapasiteetti) {
		this.m_kapasiteetti = m_kapasiteetti;
	}
	public String getM_pinta_ala() {
		return m_pinta_ala;
	}
	public void setM_pinta_ala(String m_pinta_ala) {
		this.m_pinta_ala = m_pinta_ala;
	}
	public String getM_kuvaus() {
		return m_kuvaus;
	}
	public void setM_kuvaus(String m_kuvaus) {
		this.m_kuvaus = m_kuvaus;
	}
	public String getM_kategoria() {
		return m_kategoria;
	}
	public void setM_kategoria(String m_kategoria) {
		this.m_kategoria = m_kategoria;
	}
	public String getM_hinta() {
		return m_hinta;
	}
	public void setM_hinta(String m_hinta) {
		this.m_hinta = m_hinta;
	}
	public String getM_nimi() {
		return m_nimi;
	}
	public void setM_nimi(String m_nimi) {
		this.m_nimi = m_nimi;
	}
	public String getM_alv() {
		return m_alv;
	}
	public void setM_alv(String m_alv) {
		this.m_alv = m_alv;
	}
	public String getM_toimipiste_ID() {
		return m_toimipiste_ID;
	}
	public void setM_toimipiste_ID(String m_toimipiste_ID) {
		this.m_toimipiste_ID = m_toimipiste_ID;
	}
	
	public List getListaTP (String kategoria, String toimipiste) {
		List holder = new ArrayList();
		sql = "SELECT mp_id, nimi FROM MP WHERE kategoria='" + kategoria +"' AND toimipiste_ID='" + toimipiste + "';";
		ResultSet tulokset = yhteysTesti.TuoLista(sql);
		
		try {
			while(tulokset.next()) {
				holder.add(tulokset.getString("mp_id") + " " + tulokset.getString("nimi"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return holder;
	}
	
	
	public List getLista (String kategoria) {
		List holder = new ArrayList();
		sql = "SELECT mp_id, nimi FROM MP WHERE kategoria='" + kategoria +"';";
		ResultSet tulokset = yhteysTesti.TuoLista(sql);
		
		try {
			while(tulokset.next()) {
				holder.add(tulokset.getString("mp_id") + " " + tulokset.getString("nimi"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return holder;
	}
	
	/**
	 * Tämä metodi lisää uuden palvelun tai mökin
	 * @param p_kapasiteetti Mökin kapasiteetti
	 * @param p_pinta_ala Mökin pinta-ala
	 * @param p_kuvaus Mökin / palvelun kuvaus
	 * @param p_kategoria Mökki vai Palvelu
	 * @param p_hinta Mökin / palvelun hinta
	 * @param p_nimi Mökin / palvelun nimi
	 * @param p_alv Mökin / palvelun alv
	 * @param p_toimipiste_ID mihin toimipisteeseen kuuluu
	 */
	public void lisaa(String p_kapasiteetti, String p_pinta_ala, String p_kuvaus, String p_kategoria, String p_hinta, String p_nimi, String p_alv, String p_toimipiste_ID) {
		sql = "INSERT INTO MP(kapasiteetti, pinta_ala, kuvaus, kategoria, hinta, nimi, alv, toimipiste_ID) VALUES(" + p_kapasiteetti + ", " + p_pinta_ala + ", '" + p_kuvaus + "', '" + p_kategoria + "', " + p_hinta + ", '" + p_nimi + "', " + p_alv + ", " + p_toimipiste_ID +");";
		yhteysTesti.Paivita(sql);
		}

	/**
	 * Poista haluttu mökki tai palvelu
	 * @param p_id Halutun mökin tai palvelun ID
	 */
	public void poista(String p_id ) {
		sql = "DELETE FROM MP WHERE mp_id =" + p_id + ";";
		yhteysTesti.Paivita(sql);
		}

	/**
	 * Päivitä haluttu mökki tai palvelu
	 * @param p_kapasiteetti Uusi kapasiteetti
	 * @param p_pinta_ala Uusi pinta-ala
	 * @param p_kuvaus Uusi kuvaus
	 * @param p_kategoria Uusi kategoria
	 * @param p_hinta Uusi hinta
	 * @param p_nimi Uusi nimi
	 * @param p_alv Uusi alv
	 * @param p_toimipiste_ID Uusi toimipiste
	 * @param p_id Halutun rivin ID
	 */
	public void paivita(String p_kapasiteetti, String p_pinta_ala, String p_kuvaus, String p_kategoria, String p_hinta, String p_nimi, String p_alv, String p_toimipiste_ID, String p_id) {
		sql = "UPDATE MP "
				+ "SET kapasiteetti =" + p_kapasiteetti + ", pinta_ala = " + p_pinta_ala + ", kuvaus = '" + p_kuvaus + "', kategoria = '" + p_kategoria + "', hinta = " + p_hinta + ", nimi = '" + p_nimi + "', alv = " + p_alv + ", toimipiste_ID = " + p_toimipiste_ID + " WHERE mp_id =" + p_id + ";"; 
		yhteysTesti.Paivita(sql);
		}

	/**
	 * Tämä metodi käy tallentamassa instanssin muuttujiin halutun rivin tietokannasta
	 * @param p_id Halutun rivin ID
	 */
	public void otaTiedot(String p_id) {
		sql = "SELECT * FROM MP WHERE mp_id =" + p_id + ";";
		ResultSet tulokset = yhteysTesti.TuoLista(sql);
		
		try {
			while(tulokset.next()) {
				m_mp_id = tulokset.getString("mp_id");
				m_kapasiteetti = tulokset.getString("kapasiteetti");
				m_pinta_ala = tulokset.getString("pinta_ala");
				m_kuvaus = tulokset.getString("kuvaus");
				m_kategoria = tulokset.getString("kategoria");
				m_hinta = tulokset.getString("hinta");
				m_nimi = tulokset.getString("nimi");
				m_alv = tulokset.getString("alv");
				m_toimipiste_ID = tulokset.getString("toimipiste_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
