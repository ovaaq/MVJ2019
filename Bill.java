package pdftest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Paavo Koivistoinen
 *
 */
public class Bill {
	
	//Lisätään tarvittavat muuttujat
	
	private static String bill_company = "Village People Oy"; 		//Laskuttajan nimi
	private static String bill_adress = "Bulevardi 15"; 			//Firman osoite
	private static String bill_adress_citycode = "00180"; 			//Firman postinumero
	private static String bill_adress_city = "Helsinki"; 			//Firman Kaupunki
	
	private static String payer = "Matti Meikäläinen"; 				//Maksajan nimi
	private static String payer_addres = "Matinkuja 12"; 			//Maksajan osoite
	private static String payer_adress_citycode = "00180"; 			//Maksajan postinumero
	private static String payer_adress_city = "Mattila"; 			//Maksajan Kaupunki

	private static float bill_total_no_tax = 0; 					//Yhteensä ilman arvonlisäveroa
	private static float bill_tax = 0; 								//Arvonlisävero yhteensä
	private static float bill_total = 0; 							//Maksettava yhteensä
	
	private static String bill_number = "123456789"; 				//Laskun numero
	private static String bill_created_date = "DD.MM.YYYY"; 		//Laskun pvm
	private static String bill_exp = "DD.MM.YYYY"; 					//Eräpäivä
	
	private static String bill_sent_date = "DD.MM.YYYY";			//Toimituspäivä
	private static String bill_term = "14 pv netto"; 				//Maksuehto
	private static String bill_ref_num = "1234"; 					//Viitenumero
	private static String bill_deliver_type = "Kirje"; 				//Toimitustapa
	private static String bill_buyer_id = "123456789"; 				//Ostajan tilausnumero
	private static String bill_interest = "7,50%"; 					//Viivästyskorko
	private static String bill_note_time = "7 pv"; 					//Huomautusaika
	
	private static String bill_iban = "FI21 1234 5600 0007 85"; 	//IBAN
	private static String bill_bic = "NDEAFIHH"; 					//BIC
	private static String bill_y = "1234567-8"; 					//Y-tunnus
	
	public List getAllBills() {
		//Hae kaikkien laskujen tunnistukset ja palauta 
		List allBills = new ArrayList();
		return allBills;
	}
	
	public static void updateInfo(int BILL_ID) {
		//Hae tietokannasta lasku_id:n avulla tiedot ja lisää ne muuttujiin
	}

	/**
	 * Tämä metodi luo PDF-solun halutulla tekstillä ja rajalla
	 * @param text Haluttu teksti solun sisään
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
	
	/**
	 * Tämä metodi luo PDF-solun halutulla tekstillä ilman rajaa
	 * @param text Haluttu teksti solun sisään
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

	/**
	 * Tämä metodi luo halutusta laskusta PDF-tiedoston
	 * @param BILL_ID Halutun laskun ID
	 */
	public static void CreatePDF(int BILL_ID) {
		updateInfo(BILL_ID);
		Document document = new Document();
	      try
	      {
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("L" + bill_number + ".pdf"));
	         document.open();
	         document.add(new Paragraph(bill_company));
	         
		     PdfPCell a1 = createCellNB("Laskun numero");			//Luodaan tarvittavat solut
		     PdfPCell a2 = createCellNB(bill_number);
		     PdfPCell a3 = createCellNB("Laskun pvm");
		     PdfPCell a4 = createCellNB(bill_created_date);
		     PdfPCell a5 = createCellNB("Eräpäivä");
		     PdfPCell a6 = createCellNB(bill_exp);

		     PdfPCell b1 = createCellNB("Toimituspäivä");
		     PdfPCell b2 = createCellNB(bill_sent_date);
		     PdfPCell b3 = createCellNB("Maksuehto");
		     PdfPCell b4 = createCellNB(bill_term);
		     PdfPCell b5 = createCellNB("Viitenumero");
		     PdfPCell b6 = createCellNB(bill_ref_num);
		     PdfPCell b7 = createCellNB("Toimitustapa");
		     PdfPCell b8 = createCellNB(bill_deliver_type);
		     PdfPCell b9 = createCellNB("Ostajan tilausnumero");
		     PdfPCell b10 = createCellNB(bill_buyer_id);
		     PdfPCell b11 = createCellNB("Viivästyskorko");
		     PdfPCell b12 = createCellNB(bill_interest);
		     PdfPCell b13 = createCellNB("Huomautusaika");
		     PdfPCell b14 = createCellNB(bill_note_time);

		     PdfPCell c1 = createCellNB("Kuvaus");
		     PdfPCell c2 = createCellNB("Yksikköhinta €");
		     PdfPCell c3 = createCellNB("Määrä");
		     PdfPCell c4 = createCellNB("ALV %");
		     PdfPCell c5 = createCellNB("Yhteensä €");
		     
		     PdfPCell d1 = createCellNB("Yhteensä ilman arvonlisäveroa €");
		     PdfPCell d2 = createCellNB(String.valueOf(bill_total_no_tax));
		     PdfPCell d3 = createCellNB("Arvonlisävero yhteensä €");
		     PdfPCell d4 = createCellNB(String.valueOf(bill_tax));
		     PdfPCell d5 = createCellNB("Maksettava yhteensä €");
		     PdfPCell d6 = createCellNB(String.valueOf(bill_total));

		     PdfPCell e1 = createCellB("Eräpäivä");
		     PdfPCell e2 = createCellB("Viitenumero");
		     PdfPCell e3 = createCellB("Yhteensä €");
		     PdfPCell e4 = createCellB(bill_exp);
		     PdfPCell e5 = createCellB(bill_ref_num);
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
		     PdfPCell e22 = createCellB(payer);
		     PdfPCell e23 = createCellB(payer_addres);
		     PdfPCell e24 = createCellB(payer_adress_citycode + " " + payer_adress_city);
		     
		     PdfPCell x1 = createCellNB("");
		     PdfPCell x2 = createCellNB("");

		     e1.setBorder(5);										//Muutetaan solujen rajoja sopiviksi
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

		     PdfPTable tableA = new PdfPTable(2); 					//Taulukko A
		     float[] columnsA = {1f, 1f};							//sarakkeiden jakaumat
		     tableA.setWidthPercentage(40);							//taulukon leveys
		     tableA.setSpacingAfter(10f); 							//paljonko väliä taulukon jälkeen
		     tableA.setHorizontalAlignment(Element.ALIGN_RIGHT);	//Taulukon kohdistus
		     tableA.setWidths(columnsA);							//lisätään sarakkaiden jaukama taulukkoon
		     tableA.addCell(a1);									//Lisätään solut taulukkoon
		     tableA.addCell(a2);
		     tableA.addCell(a3);
		     tableA.addCell(a4);
		     tableA.addCell(a5);
		     tableA.addCell(a6);
		     
		     PdfPTable tableB = new PdfPTable(2); 					//Taulukko B
		     float[] columnsB = {1f, 1f};							//sarakkeiden jakaumat
		     tableB.setWidthPercentage(60);							//taulukon leveys
		     tableB.setSpacingAfter(100f); 							//paljonko väliä taulukon jälkeen
		     tableB.setHorizontalAlignment(Element.ALIGN_LEFT);		//Taulukon kohdistus
		     tableB.setWidths(columnsB);	 						//lisätään sarakkaiden jaukama taulukkoon
		     tableB.addCell(b1);									//Lisätään solut taulukkoon
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

		     PdfPTable tableC = new PdfPTable(5); 					//Taulukko C
		     float[] columnsC = {20f, 8f, 4f, 4f, 6f};				//sarakkeiden jakaumat
		     tableC.setWidthPercentage(100);						//taulukon leveys
		     tableC.setSpacingAfter(10f); 							//paljonko väliä taulukon jälkeen
		     tableC.setWidths(columnsC);							//lisätään sarakkaiden jaukama taulukkoon
		     tableC.addCell(x1);									//Lisätään solut taulukkoon									
		     tableC.addCell(x1);							
		     tableC.addCell(x1);							
		     tableC.addCell(x1);
		     tableC.addCell(x1);
		     tableC.addCell(c1);							
		     tableC.addCell(c2);
		     tableC.addCell(c3);
		     tableC.addCell(c4);
		     tableC.addCell(c5);
		     
		     //Tähän väliin vielä viritelmä joka hakee listaa mökit ja palvelut
		     
		     tableC.addCell(x2);										
		     tableC.addCell(x2);							
		     tableC.addCell(x2);							
		     tableC.addCell(x2);								
		     tableC.addCell(x2);

		     PdfPTable tableD = new PdfPTable(2); 					//Taulukko D
		     float[] columnsD = {1f, 1f};							//sarakkeiden jakaumat
		     tableD.setWidthPercentage(75);							//taulukon leveys
		     tableD.setSpacingAfter(100f); 							//paljonko väliä taulukon jälkeen
		     tableD.setHorizontalAlignment(Element.ALIGN_LEFT);		//Taulukon kohdistus
		     tableD.setWidths(columnsD);							//lisätään sarakkaiden jaukama taulukkoon
		     tableD.addCell(d1);									//Lisätään solut taulukkoon
		     tableD.addCell(d2);
		     tableD.addCell(d3);
		     tableD.addCell(d4);
		     tableD.addCell(d5);
		     tableD.addCell(d6);

		     PdfPTable tableE = new PdfPTable(3); 					//Taulukko E
		     float[] columnsE = {10f, 4f, 6f};						//sarakkeiden jakaumat
		     tableE.setWidthPercentage(100);	      				//taulukon leveys	
		     tableE.setSpacingAfter(10f); 							//paljonko väliä taulukon jälkeen
		     tableE.setWidths(columnsE);	      					//lisätään sarakkaiden jaukama taulukkoon
		     tableE.addCell(e1);									//Lisätään solut taulukkoon
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

		     document.add(tableA);									//Lisätään taulut PDF-tiedostoon
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