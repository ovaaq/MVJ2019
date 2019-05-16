

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

public class Raportti {
	

	
	public static void updateCottageData(String first_date, String last_date, List Locations) {
		//Hae tietokannasta tiedot ja lisää ne muuttujaan
	}
	
	public static void updateServiceData(String first_date, String last_date, List Locations) {
		//Hae tietokannasta tiedot ja lisää ne muuttujaan
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
		
	public static void CreatePdfReportCottage(String first_date, String last_date, List Locations) {
		List data = new ArrayList();
		  String toimipisteet = "";
		  String dummy ="";
		  Yhteys yhteysTesti = new Yhteys();
		  String sql;
		  List Toimipisteet = new ArrayList();

		
		for(int i = 0; i<Locations.size(); i++) {

		sql = "SELECT nimi FROM TOIMIPISTEET WHERE toimipiste_ID ="+ Locations.get(i) +";";
        ResultSet toimipaikkoja = yhteysTesti.TuoLista(sql);
        try {
        	while (toimipaikkoja.next())
        	{
        		Toimipisteet.add(toimipaikkoja.getString("nimi"));
        	}
						} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

		
		
		Document document = new Document();

		try {
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("raportti_majoitus.pdf"));
        document.open();
        
	    PdfPCell x1 = createCellNB("");
	    PdfPCell x2 = createCellNB("");
	     
	    PdfPCell c1 = createCellNB("Mökki");
	    PdfPCell c2 = createCellNB("Aikaväli");
	    PdfPCell c3 = createCellNB("Asiakas");
	    PdfPCell c5 = createCellNB("Yhteensä ");


	    x1.setBorder(1);
	    x2.setBorder(2);
	    
	    
        document.add(new Paragraph("Majoitusraportti"));
        
		for(int i = 0; i<Locations.size(); i++) {
			if(i > 0) {
				toimipisteet = toimipisteet + ", " + Toimipisteet.get(i);
			}
			else {
				toimipisteet = toimipisteet + Toimipisteet.get(i);
			}
		}
		
		toimipisteet = toimipisteet +" " + first_date + " & " + last_date;
        document.add(new Paragraph(toimipisteet));
        document.add(new Paragraph(" "));
        
        for(int i = 0; i<Locations.size(); i++) {
            document.add(new Paragraph((String) Toimipisteet.get(i)));
            sql = "SELECT SUM(hinta*alv/100 + hinta)*(DATEDIFF(loppu_pvm, alku_pvm)+1) AS yhteensa FROM VARAUKSENHALLINTA INNER JOIN VARAUKSET, ASIAKKAAT, MP WHERE MP.mp_id = VARAUKSENHALLINTA.mp_id AND VARAUKSENHALLINTA.varaus_ID = VARAUKSET.varaus_ID AND ASIAKKAAT.asiakas_ID = VARAUKSET.asiakas_ID AND MP.kategoria = 'mokki' AND VARAUKSENHALLINTA.varaus_ID IN (SELECT varaus_ID FROM VARAUKSET WHERE toimipiste_ID ="+ Locations.get(i) +" ) AND DATE('"+first_date+"') <= alku_pvm AND DATE('"+last_date+"') >= loppu_pvm;";
            ResultSet tieto = yhteysTesti.TuoLista(sql);
            try {
            	while (tieto.next())
            	{
            		dummy = tieto.getString(1);
            	}
							} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            document.add(new Paragraph("Vuokratut mökit yhteensä: " + dummy));

            
            PdfPTable tableX = new PdfPTable(4); 					//Taulukko C
    	    float[] columnsX = {8f, 12f, 8f, 8f};				//sarakkeiden jakaumat
    	    tableX.setWidthPercentage(100);						//taulukon leveys
    	    tableX.setSpacingAfter(30f); 							//paljonko väliä taulukon jälkeen
    	    tableX.setSpacingBefore(20f);
    	    tableX.setWidths(columnsX);							//lisätään sarakkaiden jaukama taulukkoon
    	    tableX.addCell(x1);									//Lisätään solut taulukkoon									
    	    tableX.addCell(x1);							
    	    tableX.addCell(x1);
    	    tableX.addCell(x1);
    	    tableX.addCell(c1);							
    	    tableX.addCell(c2);
    	    tableX.addCell(c3);
    	    tableX.addCell(c5);
    	    
    	    sql = "SELECT nimi, CONCAT(etunimi, ' ', sukunimi) AS asiakas, CONCAT(alku_pvm,' & ', loppu_pvm) AS aika, (hinta*alv/100 + hinta)*(DATEDIFF(loppu_pvm, alku_pvm)+1) AS yhteensa FROM VARAUKSENHALLINTA INNER JOIN VARAUKSET, ASIAKKAAT, MP WHERE MP.mp_id = VARAUKSENHALLINTA.mp_id AND VARAUKSENHALLINTA.varaus_ID = VARAUKSET.varaus_ID AND kategoria = 'mokki' AND ASIAKKAAT.asiakas_ID = VARAUKSET.asiakas_ID AND VARAUKSENHALLINTA.varaus_ID IN (SELECT varaus_ID FROM VARAUKSET WHERE toimipiste_ID ="+Locations.get(i)+") AND DATE('"+first_date+"') <= alku_pvm AND DATE('"+last_date+"') >= loppu_pvm;";
            ResultSet lisaatietoa = yhteysTesti.TuoLista(sql);
            try {
            	while (lisaatietoa.next())
            	{
            	    tableX.addCell(createCellNB(lisaatietoa.getString("nimi")));
            	    tableX.addCell(createCellNB(lisaatietoa.getString("aika")));
            	    tableX.addCell(createCellNB(lisaatietoa.getString("asiakas")));
            	    tableX.addCell(createCellNB(lisaatietoa.getString("yhteensa")));

            	}
							} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	    
    	    tableX.addCell(x2);										
    	    tableX.addCell(x2);							
    	    tableX.addCell(x2);								
    	    tableX.addCell(x2);
    	    
    	    document.add(tableX);


        }




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
	
	public static void CreatePdfReportService(String first_date, String last_date, List Locations) {
		List data = new ArrayList();
		 String toimipisteet = "";
		 String dummy = "";
		 Yhteys yhteysTesti = new Yhteys();
		 String sql;
		 List Toimipisteet = new ArrayList();

		
		for(int i = 0; i<Locations.size(); i++) {

		sql = "SELECT nimi FROM TOIMIPISTEET WHERE toimipiste_ID ="+ Locations.get(i) +";";
        ResultSet toimipaikkoja = yhteysTesti.TuoLista(sql);
        try {
        	while (toimipaikkoja.next())
        	{
        		Toimipisteet.add(toimipaikkoja.getString("nimi"));
        	}
						} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

		
		
		Document document = new Document();

		try {
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("raportti_palvelut.pdf"));
        document.open();
        
	    PdfPCell x1 = createCellNB("");
	    PdfPCell x2 = createCellNB("");
	     
	    PdfPCell c1 = createCellNB("Palvelu");
	    PdfPCell c2 = createCellNB("Aikaväli");
	    PdfPCell c3 = createCellNB("Asiakas");
	    PdfPCell c5 = createCellNB("Yhteensä ");


	    x1.setBorder(1);
	    x2.setBorder(2);
	    
	    
        document.add(new Paragraph("Palveluraportti"));
        
		for(int i = 0; i<Locations.size(); i++) {
			if(i > 0) {
				toimipisteet = toimipisteet + ", " + Toimipisteet.get(i);
			}
			else {
				toimipisteet = toimipisteet + Toimipisteet.get(i);
			}
		}
		
		toimipisteet = toimipisteet +" " + first_date + " & " + last_date;
        document.add(new Paragraph(toimipisteet));
        document.add(new Paragraph(" "));
        
        for(int i = 0; i<Locations.size(); i++) {
            document.add(new Paragraph((String) Toimipisteet.get(i)));
            sql = "SELECT SUM(hinta*alv/100 + hinta) AS yhteensa FROM VARAUKSENHALLINTA INNER JOIN VARAUKSET, ASIAKKAAT, MP WHERE MP.mp_id = VARAUKSENHALLINTA.mp_id AND VARAUKSENHALLINTA.varaus_ID = VARAUKSET.varaus_ID AND ASIAKKAAT.asiakas_ID = VARAUKSET.asiakas_ID AND MP.kategoria = 'palvelu' AND VARAUKSENHALLINTA.varaus_ID IN (SELECT varaus_ID FROM VARAUKSET WHERE toimipiste_ID ="+ Locations.get(i) +" ) AND DATE('"+first_date+"') <= alku_pvm AND DATE('"+last_date+"') >= loppu_pvm;";
            ResultSet tieto = yhteysTesti.TuoLista(sql);
            try {
            	while (tieto.next())
            	{
            		dummy = tieto.getString(1);
            	}
							} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            document.add(new Paragraph("Ostetut lisäpalvelut yhteensä: " + dummy));

            
            PdfPTable tableX = new PdfPTable(4); 					//Taulukko C
    	    float[] columnsX = {8f, 12f, 8f, 8f};				//sarakkeiden jakaumat
    	    tableX.setWidthPercentage(100);						//taulukon leveys
    	    tableX.setSpacingAfter(30f); 							//paljonko väliä taulukon jälkeen
    	    tableX.setSpacingBefore(20f);
    	    tableX.setWidths(columnsX);							//lisätään sarakkaiden jaukama taulukkoon
    	    tableX.addCell(x1);									//Lisätään solut taulukkoon									
    	    tableX.addCell(x1);							
    	    tableX.addCell(x1);
    	    tableX.addCell(x1);
    	    tableX.addCell(c1);							
    	    tableX.addCell(c2);
    	    tableX.addCell(c3);
    	    tableX.addCell(c5);
    	    
    	    sql = "SELECT nimi, CONCAT(etunimi, ' ', sukunimi) AS asiakas, CONCAT(alku_pvm,' & ', loppu_pvm) AS aika, (hinta*alv/100 + hinta) AS yhteensa FROM VARAUKSENHALLINTA INNER JOIN VARAUKSET, ASIAKKAAT, MP WHERE MP.mp_id = VARAUKSENHALLINTA.mp_id AND VARAUKSENHALLINTA.varaus_ID = VARAUKSET.varaus_ID AND kategoria = 'palvelu' AND ASIAKKAAT.asiakas_ID = VARAUKSET.asiakas_ID AND VARAUKSENHALLINTA.varaus_ID IN (SELECT varaus_ID FROM VARAUKSET WHERE toimipiste_ID ="+Locations.get(i)+") AND DATE('"+first_date+"') <= alku_pvm AND DATE('"+last_date+"') >= loppu_pvm;";
            ResultSet lisaatietoa = yhteysTesti.TuoLista(sql);
            try {
            	while (lisaatietoa.next())
            	{
            	    tableX.addCell(createCellNB(lisaatietoa.getString("nimi")));
            	    tableX.addCell(createCellNB(lisaatietoa.getString("aika")));
            	    tableX.addCell(createCellNB(lisaatietoa.getString("asiakas")));
            	    tableX.addCell(createCellNB(lisaatietoa.getString("yhteensa")));

            	}
							} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	    
    	    tableX.addCell(x2);										
    	    tableX.addCell(x2);							
    	    tableX.addCell(x2);								
    	    tableX.addCell(x2);
    	    
    	    document.add(tableX);


        }




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
