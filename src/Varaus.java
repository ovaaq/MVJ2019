import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Varaus {
  
  private String m_varaus_ID;
  private String m_lasku_pvm;
  private String m_lasku_erapaiva;
  private String m_viitenumero;
  private String m_viivastyskorko;
  private String m_huomautusaika;
  private String m_maksuehto;
  private String m_varauspaiva;
  private String m_alku_pvm;
  private String m_loppu_pvm;
  private String m_asiakas_ID;
  private String m_toimipiste_ID;
  private String varausnumero;
  private String m_status;
  
  private String sql;
  private Yhteys yhteysTesti = new Yhteys();

  
  public String getMstatus() {
	    return m_status;
	  }
	  public void setM_status(String status) {
	    m_status = status;
	  }
  
  public String getM_varaus_ID() {
    return m_varaus_ID;
  }
  public void setM_varaus_ID(String m_varaus_ID) {
    this.m_varaus_ID = m_varaus_ID;
  }
  public String getM_lasku_pvm() {
    return m_lasku_pvm;
  }
  public void setM_lasku_pvm(String m_lasku_pvm) {
    this.m_lasku_pvm = m_lasku_pvm;
  }
  public String getM_lasku_erapaiva() {
    return m_lasku_erapaiva;
  }
  public void setM_lasku_erapaiva(String m_lasku_erapaiva) {
    this.m_lasku_erapaiva = m_lasku_erapaiva;
  }
  public String getM_viitenumero() {
    return m_viitenumero;
  }
  public void setM_viitenumero(String m_viitenumero) {
    this.m_viitenumero = m_viitenumero;
  }
  public String getM_viivastyskorko() {
    return m_viivastyskorko;
  }
  public void setM_viivastyskorko(String m_viivastyskorko) {
    this.m_viivastyskorko = m_viivastyskorko;
  }
  public String getM_huomautusaika() {
    return m_huomautusaika;
  }
  public void setM_huomautusaika(String m_huomautusaika) {
    this.m_huomautusaika = m_huomautusaika;
  }
  public String getM_maksuehto() {
    return m_maksuehto;
  }
  public void setM_maksuehto(String m_maksuehto) {
    this.m_maksuehto = m_maksuehto;
  }
  public String getM_varauspaiva() {
    return m_varauspaiva;
  }
  public void setM_varauspaiva(String m_varauspaiva) {
    this.m_varauspaiva = m_varauspaiva;
  }
  public String getM_alku_pvm() {
    return m_alku_pvm;
  }
  public void setM_alku_pvm(String m_alku_pvm) {
    this.m_alku_pvm = m_alku_pvm;
  }
  public String getM_loppu_pvm() {
    return m_loppu_pvm;
  }
  public void setM_loppu_pvm(String m_loppu_pvm) {
    this.m_loppu_pvm = m_loppu_pvm;
  }
  public String getM_asiakas_ID() {
    return m_asiakas_ID;
  }
  public void setM_asiakas_ID(String m_asiakas_ID) {
    this.m_asiakas_ID = m_asiakas_ID;
  }
  public String getM_toimipiste_ID() {
    return m_toimipiste_ID;
  }
  public void setM_toimipiste_ID(String m_toimipiste_ID) {
    this.m_toimipiste_ID = m_toimipiste_ID;
  }
  
/*
   * Metodiden varauksen
   * @param p_lasku_pvm 
   * @param p_lasku_erapaiva
   * @param p_viitenumero
   * @param p_viivastyskorko
   * @param p_huomautusaika
   * @param p_maksuehto
   * @param p_varauspaiva
   * @param p_alku_pvm
   * @param p_loppu_pvm
   * @param p_asiakas_ID
   * @param p_toimipiste_ID
   */
  
  public void lisaa(String p_lasku_pvm, String p_lasku_erapaiva, String p_viitenumero, String p_viivastyskorko, String p_huomautusaika, String p_maksuehto, String p_varauspaiva, String p_alku_pvm, String p_loppu_pvm, String p_asiakas_ID, String p_toimipiste_ID, List palvelut) {
    sql = "INSERT INTO VARAUKSET (lasku_pvm, lasku_erapaiva, viitenumero, viivastyskorko, huomautusaika, maksuehto, varauspaiva, alku_pvm, loppu_pvm, asiakas_ID, toimipiste_ID) VALUES('" + p_lasku_pvm + "', '" + p_lasku_erapaiva + "', '" + p_viitenumero + "', '" + p_viivastyskorko + "', '" + p_huomautusaika + "', '" + p_maksuehto + "', '" + p_varauspaiva + "', '" + p_alku_pvm +  "', '" + p_loppu_pvm + "', '" + p_asiakas_ID + "', '" + p_toimipiste_ID + "');";
    yhteysTesti.Paivita(sql);
    
    sql = "SELECT varaus_ID FROM VARAUKSET WHERE varauspaiva='" + p_varauspaiva + "'AND alku_pvm ='"+ p_alku_pvm +"' AND asiakas_ID=" + p_asiakas_ID + ";";
    
    ResultSet lasku_id = yhteysTesti.TuoLista(sql);
    
    try {
      while(lasku_id.next()) {
      varausnumero = lasku_id.getString("varaus_ID");
      }
    }catch(Exception e){
      e.getStackTrace();
    }
    for(int i = 0; i < palvelut.size(); i++) {
      sql = "INSERT INTO VARAUKSENHALLINTA (mp_id, varaus_ID) VALUES(" + palvelut.get(i) + "," + varausnumero + ");";
      yhteysTesti.Paivita(sql);
    }
  }
  
  /*
   * Metodi poistaa halutun varauksen
   * @param p_id
   */
  public void poista(String p_id ) {
    sql = "DELETE FROM VARAUKSET WHERE varaus_ID =" + p_id + ";";
    yhteysTesti.Paivita(sql);
    sql = "DELETE FROM VARAUKSENHALLINTA WHERE varaus_ID=" + p_id + ";";
    yhteysTesti.Paivita(sql);
    }
  
  public void Piilota(String p_id ) {
	    sql = " UPDATE VARAUKSET SET piilotettu = 'kylla' WHERE varaus_ID =" + p_id + ";";
	    yhteysTesti.Paivita(sql);

	    }
  
  public void MuutaStatus(String p_id, String status ) {
	    sql = " UPDATE VARAUKSET SET maksettu = '"+ status+"' WHERE varaus_ID =" + p_id + ";";
	    yhteysTesti.Paivita(sql);

	    }
  
  
  
  /*
   * Metodi pivitt halutun varaukset
   * @param p_lasku_pvm
   * @param p_lasku_erapaiva
   * @param p_viitenumero
   * @param p_viivastyskorko
   * @param p_huomautusaika
   * @param p_maksuehto
   * @param p_varauspaiva
   * @param p_alku_pvm
   * @param p_loppu_pvm
   * @param p_asiakas_ID
   * @param p_toimipiste_ID
   * @param p_varaus_ID
   */
  
  public void paivita(String p_lasku_pvm, String p_lasku_erapaiva, String p_viitenumero, String p_viivastyskorko, String p_huomautusaika, String p_maksuehto, String p_varauspaiva, String p_alku_pvm, String p_loppu_pvm, String p_asiakas_ID, String p_toimipiste_ID, String p_varaus_ID, List palvelut) {
    sql = "UPDATE VARAUKSET "
        + "SET lasku_pvm ='" + p_lasku_pvm + "', lasku_erapaiva = '" + p_lasku_erapaiva + "', viitenumero = '" + p_viitenumero + "', viivastyskorko = '" + p_viivastyskorko + "', huomautusaika = '" + p_huomautusaika + "', maksuehto = '" + p_maksuehto + "', varauspaiva = '" + p_varauspaiva + "', alku_pvm = '" + p_alku_pvm + "', loppu_pvm = '" + p_loppu_pvm + "', asiakas_ID = '" + p_asiakas_ID + "', toimipiste_ID = '" + p_toimipiste_ID + "' WHERE varaus_ID ='" + p_varaus_ID + "';"; 
    yhteysTesti.Paivita(sql);
    
    
    sql = "DELETE FROM VARAUKSENHALLINTA WHERE varaus_ID=" + p_varaus_ID + ";";
    yhteysTesti.Paivita(sql);
    for(int i = 0; i < palvelut.size(); i++) {
      sql = "INSERT INTO VARAUKSENHALLINTA (mp_id, varaus_ID) VALUES(" + palvelut.get(i) + "," + p_varaus_ID + ");";
      yhteysTesti.Paivita(sql);
    }
    }
  
    

  /*
   * Metodi ker ja palauttaa listan kaikista varauksista
   * @return Lista varauksista
   */
  public List getLista() {
	    List holder = new ArrayList();
	    sql = "SELECT VARAUKSET.varaus_ID , ASIAKKAAT.etunimi, ASIAKKAAT.sukunimi, VARAUKSET.varauspaiva FROM VARAUKSET INNER JOIN ASIAKKAAT ON ASIAKKAAT.asiakas_ID=VARAUKSET.asiakas_ID;";
	    ResultSet tulokset = yhteysTesti.TuoLista(sql);
	    
	    try {
	      while(tulokset.next()) {
	        holder.add(tulokset.getString("varaus_ID") + " " + tulokset.getString("etunimi")+ tulokset.getString("sukunimi")+" "+tulokset.getString("varauspaiva"));
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    return holder;
	  }
  

	  
	  public List getListaAsiakas(String p_asiakas_ID) {
	    List holder = new ArrayList();
	    sql = "SELECT VARAUKSET.varaus_ID , ASIAKKAAT.etunimi, ASIAKKAAT.sukunimi, VARAUKSET.varauspaiva FROM VARAUKSET INNER JOIN ASIAKKAAT ON ASIAKKAAT.asiakas_ID=VARAUKSET.asiakas_ID WHERE VARAUKSET.asiakas_ID ="+p_asiakas_ID+";";
	    ResultSet tulokset = yhteysTesti.TuoLista(sql);
	    
	    try {
	      while(tulokset.next()) {
	        holder.add(tulokset.getString("varaus_ID") + " " + tulokset.getString("etunimi")+ tulokset.getString("sukunimi")+" " + tulokset.getString("varauspaiva"));
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    
	    return holder;
	  }
	  
	  
	  public List getListaAsiakasFiltteri(String p_asiakas_ID) {
		    List holder = new ArrayList();
		    sql = "SELECT VARAUKSET.varaus_ID , ASIAKKAAT.etunimi, ASIAKKAAT.sukunimi, VARAUKSET.varauspaiva FROM VARAUKSET INNER JOIN ASIAKKAAT ON ASIAKKAAT.asiakas_ID=VARAUKSET.asiakas_ID WHERE VARAUKSET.asiakas_ID ="+p_asiakas_ID+" AND VARAUKSET.piilotettu = 'ei';";
		    ResultSet tulokset = yhteysTesti.TuoLista(sql);
		    
		    try {
		      while(tulokset.next()) {
		        holder.add(tulokset.getString("varaus_ID") + " " + tulokset.getString("etunimi")+ tulokset.getString("sukunimi")+" " + tulokset.getString("varauspaiva"));
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    
		    return holder;
		  }
	  
	  public String getMokki() {
		    String holder = "";
		    sql = "SELECT MP.nimi FROM VARAUKSENHALLINTA INNER JOIN MP ON VARAUKSENHALLINTA.mp_id = MP.mp_id WHERE kategoria = 'mokki' AND varaus_ID =" +m_varaus_ID+ ";";
		    ResultSet tulokset = yhteysTesti.TuoLista(sql);
		    
		    try {
		      while(tulokset.next()) {
		        holder = (tulokset.getString("nimi"));
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    
		    return holder;
		  }
		  
		  public String getPalvelu() {
		    String holder = "";
		    sql = "SELECT MP.nimi FROM VARAUKSENHALLINTA INNER JOIN MP ON VARAUKSENHALLINTA.mp_id = MP.mp_id WHERE kategoria = 'palvelu' AND varaus_ID =" +m_varaus_ID+ ";";
		    ResultSet tulokset = yhteysTesti.TuoLista(sql);
		    
		    try {
		      while(tulokset.next()) {
			        holder = (tulokset.getString("nimi"));
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    
		    return holder;
		  }


  
  /*
   * P��ivitt���� instanssiin tiedot halutusta varauksesta
   * @param p_id
   */
  public void otaTiedot(String p_id) {
    sql = "SELECT * FROM VARAUKSET WHERE varaus_ID =" + p_id + ";";
    ResultSet tulokset = yhteysTesti.TuoLista(sql);
    
    try {
      while(tulokset.next()) {
        m_varaus_ID = tulokset.getString("varaus_ID");
        m_lasku_pvm = tulokset.getString("lasku_pvm");
        m_lasku_erapaiva = tulokset.getString("lasku_erapaiva");
        m_viitenumero = tulokset.getString("viitenumero");
        m_viivastyskorko = tulokset.getString("viivastyskorko");
        m_huomautusaika = tulokset.getString("huomautusaika");
        m_maksuehto = tulokset.getString("maksuehto");
        m_varauspaiva = tulokset.getString("varauspaiva");
        m_alku_pvm = tulokset.getString("alku_pvm");
        m_loppu_pvm = tulokset.getString("loppu_pvm");
        m_asiakas_ID = tulokset.getString("asiakas_ID");
        m_toimipiste_ID = tulokset.getString("toimipiste_ID");
        m_status = tulokset.getString("maksettu");

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }}



