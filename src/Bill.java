import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Font;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/*
 * @author Paavo Koivistoinen
 *
 */
public class Bill {
  
  //Lis��t����n tarvittavat muuttujat
  
 
  
  
  public static int MvaiP(String palvelu, int kesto ) {
    int palautus = 1;
      if (palvelu.toLowerCase().equals("mokki")){
        palautus = kesto;
      }
    return palautus;
  }

  
  public static void otaTiedot(String p_id) {
	  
    
    


  }

  
  public List getAllBills() {
    //Hae kaikkien laskujen tunnistukset ja palauta 
    List allBills = new ArrayList();
    return allBills;
  }
  
  public static void updateInfo(int BILL_ID) {
    //Hae tietokannasta lasku_id:n avulla tiedot ja lis���� ne muuttujiin
  }

  /*
   * T��m�� metodi luo PDF-solun halutulla tekstill�� ja rajalla
   * @param text Haluttu teksti solun sis����n
   * @return pdf-solu taulukkoa varten
   */
  private static PdfPCell createCellB(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        cell.setBorder(14);
        cell.setPaddingBottom(5f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    return cell;
  }
  
  /*
   * T��m�� metodi luo PDF-solun halutulla tekstill�� ilman rajaa
   * @param text Haluttu teksti solun sis����n
   * @return pdf-solu taulukkoa varten
   */
  private static PdfPCell createCellNB(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        cell.setBorder(0);
        cell.setPaddingBottom(5f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    return cell;
  }
/*
   * T��m�� metodi luo halutusta laskusta PDF-tiedoston
   * @param BILL_ID Halutun laskun ID
   */
  public static void CreatePDF(String varaus_ID, String toimitustapa) {
	    
	  
	  String bill_company = "Village People Oy";     //Laskuttajan nimi
	   String bill_adress = "Bulevardi 15";       //Firman osoite
	   String bill_adress_citycode = "00180";       //Firman postinumero
	 String bill_adress_city = "Helsinki";       //Firman Kaupunki
	  
	   String m_etunimi = "Matti";         //Maksajan nimi
	   String m_sukunimi = "Meik��l��inen";
	   String m_lahiosoite = "Matinkuja 12";       //Maksajan osoite
	   String m_postinumero = "00180";       //Maksajan postinumero
	   String m_paikkakunta = "Mattila";       //Maksajan Kaupunki
	   String m_email = "";

	   float bill_total_no_tax = 0;           //Yhteens�� ilman arvonlis��veroa
	   float bill_tax = 0;                 //Arvonlis��vero yhteens��
	   float bill_total = 0;               //Maksettava yhteens��
	  
	   String bill_number = "123456789";         //Laskun numero
	   String m_lasku_pvm = "DD.MM.YYYY";     //Laskun pvm
	   String m_lasku_erapaiva = "DD.MM.YYYY";           //Er��p��iv��
	  
	   String bill_sent_date = "DD.MM.YYYY";      //Toimitusp��iv��
	   String m_maksuehto = "14 pv netto";         //Maksuehto
	   String bill_ref_num = "1234";           //Viitenumero
	   String bill_deliver_type = "Kirje";         //Toimitustapa
	   String m_asiakas_ID = "123456789";         //Ostajan tilausnumero
	   String m_viivastuskorko = "7,50%";           //Viiv��styskorko
	   String m_huomautusaika = "7 pv";           //Huomautusaika
	  
	   String bill_iban = "FI21 1234 5600 0007 85";   //IBAN
	   String bill_bic = "NDEAFIHH";           //BIC
	   String bill_y = "1234567-8";           //Y-tunnus
	  
	   String m_varauspaiva= "";
	   String m_alku_pvm = "";
	   String m_loppu_pvm= "";
	   String m_toimipiste_ID= "";
	   String m_varaus_ID = "";
	   int m_kesto;
	  
	   List<String> tuotteet = new ArrayList<>();

	  
	  String sql;
	  Yhteys yhteys = new Yhteys();
	  
    bill_deliver_type = toimitustapa;
    
    System.out.println(5);
    sql = "SELECT * FROM VARAUKSET WHERE varaus_ID =" + varaus_ID + ";";
    ResultSet perustiedot = yhteys.TuoLista(sql);
    System.out.println(6);
    
    try {
      while(perustiedot.next()) {
        m_varaus_ID = perustiedot.getString("varaus_ID");
        m_asiakas_ID = perustiedot.getString("asiakas_ID");
        m_lasku_pvm = perustiedot.getString("lasku_pvm");
        m_lasku_erapaiva = perustiedot.getString("lasku_erapaiva");
        m_viivastuskorko = perustiedot.getString("viivastyskorko");
        m_huomautusaika = perustiedot.getString("huomautusaika");
        m_maksuehto = perustiedot.getString("maksuehto");
m_varauspaiva = perustiedot.getString("varauspaiva");
        m_alku_pvm = perustiedot.getString("alku_pvm");
        m_loppu_pvm = perustiedot.getString("loppu_pvm");
        m_toimipiste_ID = perustiedot.getString("toimipiste_ID");

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println(1);
    sql = "SELECT * FROM ASIAKKAAT WHERE asiakas_ID =" + m_asiakas_ID + ";";
    ResultSet maksaja = yhteys.TuoLista(sql);
    System.out.println(2);
    try {
      while(maksaja.next()) {
        m_etunimi = maksaja.getString("etunimi");
        m_sukunimi = maksaja.getString("sukunimi");
        m_postinumero = maksaja.getString("postinumero");
        m_paikkakunta = maksaja.getString("paikkakunta");
        m_lahiosoite = maksaja.getString("lahiosoite");
        m_email = maksaja.getString("email");

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println(3);
    sql = "SELECT nimi, hinta, kategoria, alv, ((alv/100)*hinta + hinta) AS verollinen FROM VARAUKSENHALLINTA INNER JOIN MP ON VARAUKSENHALLINTA.mp_id = MP.mp_id WHERE VARAUKSENHALLINTA.varaus_ID ="+varaus_ID +";";
    
    ResultSet palvelutjamokit = yhteys.TuoLista(sql);
    System.out.println(4);
    LocalDate dateBefore = LocalDate.parse(m_alku_pvm);
    LocalDate dateAfter = LocalDate.parse(m_loppu_pvm);
    
    m_kesto = (int) ChronoUnit.DAYS.between(dateBefore, dateAfter);
    m_kesto = m_kesto + 1;
    System.out.println(m_kesto);
    
    try {
      while(palvelutjamokit.next()) {
    	  tuotteet.add(palvelutjamokit.getString("nimi"));
          tuotteet.add(palvelutjamokit.getString("verollinen"));
          tuotteet.add(String.valueOf(MvaiP(palvelutjamokit.getString("kategoria"), m_kesto)));
          tuotteet.add(palvelutjamokit.getString("alv"));
          tuotteet.add(String.valueOf((MvaiP(palvelutjamokit.getString("kategoria"), m_kesto)*Double.valueOf(palvelutjamokit.getString("verollinen")))));
          bill_total_no_tax = bill_total_no_tax + Float.valueOf(palvelutjamokit.getString("hinta"));
          bill_total = bill_total + Float.valueOf((MvaiP(palvelutjamokit.getString("kategoria"), m_kesto)*Float.valueOf(palvelutjamokit.getString("verollinen"))));

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    bill_tax = bill_total - bill_total_no_tax;
    
    Document document = new Document();
        try
        {
           PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("lasku.pdf"));
           document.open();
           document.add(new Paragraph(bill_company));
           
         PdfPCell a1 = createCellNB("Laskun numero");      //Luodaan tarvittavat solut
         PdfPCell a2 = createCellNB(m_varaus_ID);
         PdfPCell a3 = createCellNB("Laskun pvm");
         PdfPCell a4 = createCellNB(m_lasku_pvm);
         PdfPCell a5 = createCellNB("Eräpäivä");
         PdfPCell a6 = createCellNB(m_lasku_erapaiva);

         PdfPCell b1 = createCellNB("Toimituspäivä");
         PdfPCell b2 = createCellNB(m_lasku_pvm);
         PdfPCell b3 = createCellNB("Maksuehto");
         PdfPCell b4 = createCellNB(m_maksuehto);
         PdfPCell b5 = createCellNB("Viitenumero");
         PdfPCell b6 = createCellNB(m_varaus_ID);
         PdfPCell b7 = createCellNB("Toimitustapa");
         PdfPCell b8 = createCellNB(bill_deliver_type);
         PdfPCell b9 = createCellNB("Ostajan tilausnumero");
         PdfPCell b10 = createCellNB(m_asiakas_ID);
         PdfPCell b11 = createCellNB("Viivästyskorko");
         PdfPCell b12 = createCellNB(m_viivastuskorko);
         PdfPCell b13 = createCellNB("Huomautusaika");
         PdfPCell b14 = createCellNB(m_huomautusaika);

         PdfPCell c1 = createCellNB("Kuvaus");
         PdfPCell c2 = createCellNB("Yksikköhinta €");
         PdfPCell c3 = createCellNB("Määrä");
         PdfPCell c4 = createCellNB("ALV %");
         PdfPCell c5 = createCellNB("Yhteensä €");
         
         PdfPCell d1 = createCellNB("Yhteensä ilman arvonlisäveroa €");
         PdfPCell d2 = createCellNB(String.valueOf(bill_tax));
         PdfPCell d3 = createCellNB("Arvonlisävero yhteensä €");
         PdfPCell d4 = createCellNB(String.valueOf(bill_total_no_tax));
         PdfPCell d5 = createCellNB("Maksettava yhteensä €");
         PdfPCell d6 = createCellNB(String.valueOf(bill_total));

         PdfPCell e1 = createCellB("Eräpäivä");
         PdfPCell e2 = createCellB("Viitenumero");
         PdfPCell e3 = createCellB("Yhteensä €");
         PdfPCell e4 = createCellB(m_lasku_erapaiva);
         PdfPCell e5 = createCellB(m_varaus_ID);
         PdfPCell e6 = createCellB(String.valueOf(bill_total));
         PdfPCell e7 = createCellB("IBAN");
         PdfPCell e8 = createCellB("BIC");
         PdfPCell e9 = createCellB("Y-tunnus");
         PdfPCell e10 = createCellB(bill_iban);
         PdfPCell e11 = createCellB(bill_bic);
         PdfPCell e12 = createCellB(bill_y);
         PdfPCell e13 = createCellB("Saajan tiedot");
         PdfPCell e14 = createCellB("");
         PdfPCell e15 = createCellB("");
         PdfPCell e16 = createCellB(bill_company);
         PdfPCell e17 = createCellB(bill_adress);
         PdfPCell e18 = createCellB(bill_adress_citycode + " " + bill_adress_city );
         PdfPCell e19 = createCellB("Maksajan tiedot");
         PdfPCell e20 = createCellB("");
         PdfPCell e21 = createCellB("");
         PdfPCell e22 = createCellB(m_etunimi + " " + m_sukunimi);
         PdfPCell e23 = createCellB(m_lahiosoite);
         PdfPCell e24 = createCellB(m_postinumero + " " + m_paikkakunta);
         
         PdfPCell x1 = createCellNB("");
         PdfPCell x2 = createCellNB("");
e1.setBorder(5);                    //Muutetaan solujen rajoja sopiviksi
         e2.setBorder(5);
         e3.setBorder(13);
         e7.setBorder(5);
         e8.setBorder(5);
         e9.setBorder(13);
         e13.setBorder(4);
         e14.setBorder(0);
         e15.setBorder(8);
         e16.setBorder(22);
         e17.setBorder(2);
         e18.setBorder(10);
         e19.setBorder(4);
         e20.setBorder(0);
         e21.setBorder(8);
         e22.setBorder(22);
         e23.setBorder(2);
         e24.setBorder(10);
         x1.setBorder(1);
         x2.setBorder(2);

         PdfPTable tableA = new PdfPTable(2);           //Taulukko A
         float[] columnsA = {1f, 1f};              //sarakkeiden jakaumat
         tableA.setWidthPercentage(40);              //taulukon leveys
         tableA.setSpacingAfter(10f);               //paljonko vli taulukon jlkeen
         tableA.setHorizontalAlignment(Element.ALIGN_RIGHT);  //Taulukon kohdistus
         tableA.setWidths(columnsA);              //listn sarakkaiden jaukama taulukkoon
         tableA.addCell(a1);                  //Listn solut taulukkoon
         tableA.addCell(a2);
         tableA.addCell(a3);
         tableA.addCell(a4);
         tableA.addCell(a5);
         tableA.addCell(a6);
         
         PdfPTable tableB = new PdfPTable(2);           //Taulukko B
         float[] columnsB = {1f, 1f};              //sarakkeiden jakaumat
         tableB.setWidthPercentage(60);              //taulukon leveys
         tableB.setSpacingAfter(100f);               //paljonko vli taulukon jlkeen
         tableB.setHorizontalAlignment(Element.ALIGN_LEFT);    //Taulukon kohdistus
         tableB.setWidths(columnsB);               //listn sarakkaiden jaukama taulukkoon
         tableB.addCell(b1);                  //Listn solut taulukkoon
         tableB.addCell(b2);
         tableB.addCell(b3);
         tableB.addCell(b4);
         tableB.addCell(b5);
         tableB.addCell(b6);
         tableB.addCell(b7);
         tableB.addCell(b8);
         tableB.addCell(b9);
         tableB.addCell(b10);
         tableB.addCell(b11);
         tableB.addCell(b12);
         tableB.addCell(b13);
         tableB.addCell(b14);

         PdfPTable tableC = new PdfPTable(5);           //Taulukko C
         float[] columnsC = {20f, 8f, 4f, 4f, 6f};        //sarakkeiden jakaumat
         tableC.setWidthPercentage(100);            //taulukon leveys
         tableC.setSpacingAfter(10f);               //paljonko vli taulukon jlkeen
         tableC.setWidths(columnsC);              //listn sarakkaiden jaukama taulukkoon
         tableC.addCell(x1);                  //Listn solut taulukkoon                  
         tableC.addCell(x1);              
         tableC.addCell(x1);              
         tableC.addCell(x1);
         tableC.addCell(x1);
         tableC.addCell(c1);              
         tableC.addCell(c2);
         tableC.addCell(c3);
         tableC.addCell(c4);
         tableC.addCell(c5);
         
         //Thn vliin viel viritelm joka hakee listaa mkit ja palvelut
         for(int i = 0; i < tuotteet.size(); i++) {
           tableC.addCell(createCellNB(tuotteet.get(i)));
         }

         
         tableC.addCell(x2);                    
         tableC.addCell(x2);              
         tableC.addCell(x2);              
         tableC.addCell(x2);                
         tableC.addCell(x2);
PdfPTable tableD = new PdfPTable(2);           //Taulukko D
         float[] columnsD = {1f, 1f};              //sarakkeiden jakaumat
         tableD.setWidthPercentage(75);              //taulukon leveys
         tableD.setSpacingAfter(100f);               //paljonko v��li�� taulukon j��lkeen
         tableD.setHorizontalAlignment(Element.ALIGN_LEFT);    //Taulukon kohdistus
         tableD.setWidths(columnsD);              //lis��t����n sarakkaiden jaukama taulukkoon
         tableD.addCell(d1);                  //Lis��t����n solut taulukkoon
         tableD.addCell(d2);
         tableD.addCell(d3);
         tableD.addCell(d4);
         tableD.addCell(d5);
         tableD.addCell(d6);

         PdfPTable tableE = new PdfPTable(3);           //Taulukko E
         float[] columnsE = {10f, 4f, 6f};            //sarakkeiden jakaumat
         tableE.setWidthPercentage(100);                //taulukon leveys  
         tableE.setSpacingAfter(10f);               //paljonko v��li�� taulukon j��lkeen
         tableE.setWidths(columnsE);                  //lis��t����n sarakkaiden jaukama taulukkoon
         tableE.addCell(e1);                  //Lis��t����n solut taulukkoon
         tableE.addCell(e2);
         tableE.addCell(e3);
         tableE.addCell(e4);
         tableE.addCell(e5);
         tableE.addCell(e6);
         tableE.addCell(e7);
         tableE.addCell(e8);
         tableE.addCell(e9);
         tableE.addCell(e10);
         tableE.addCell(e11);
         tableE.addCell(e12);
         tableE.addCell(e13);
         tableE.addCell(e14);
         tableE.addCell(e15);
         tableE.addCell(e16);
         tableE.addCell(e17);
         tableE.addCell(e18);
         tableE.addCell(e19);
         tableE.addCell(e20);
         tableE.addCell(e21);
         tableE.addCell(e22);
         tableE.addCell(e23);
         tableE.addCell(e24);

         document.add(tableA);                  //Lis��t����n taulut PDF-tiedostoon
         document.add(tableB);
         document.add(tableC);
         document.add(tableD);
         document.add(tableE);
         
           document.close();
           writer.close();
        } catch (DocumentException e)
        {
           e.printStackTrace();
        } catch (FileNotFoundException e)
        {
           e.printStackTrace();
        }
    
  }
}
