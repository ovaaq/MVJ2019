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

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

public class Report {
	
	List data = new ArrayList();
	
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
		Document document = new Document();

		try {
		updateCottageData(first_date, last_date, Locations);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("raportti_majoitus.pdf"));
        document.open();
        
	    PdfPCell x1 = createCellNB("");
	    PdfPCell x2 = createCellNB("");
	     
	    PdfPCell c1 = createCellNB("Mökki");
	    PdfPCell c2 = createCellNB("Aikaväli");
	    PdfPCell c3 = createCellNB("Päivät");
	    PdfPCell c5 = createCellNB("Yhteensä €");


	    x1.setBorder(1);
	    x2.setBorder(2);

	    PdfPTable tableC = new PdfPTable(4); 					//Taulukko C
	    float[] columnsC = {8f, 12f, 8f, 8f};				//sarakkeiden jakaumat
	    tableC.setWidthPercentage(100);						//taulukon leveys
	    tableC.setSpacingAfter(30f); 							//paljonko väliä taulukon jälkeen
	    tableC.setSpacingBefore(20f);
	    tableC.setWidths(columnsC);							//lisätään sarakkaiden jaukama taulukkoon
	    tableC.addCell(x1);									//Lisätään solut taulukkoon									
	    tableC.addCell(x1);							
	    tableC.addCell(x1);
	    tableC.addCell(x1);
	    tableC.addCell(c1);							
	    tableC.addCell(c2);
	    tableC.addCell(c3);
	    tableC.addCell(c5);
	    tableC.addCell(createCellNB("Kanala"));
	    tableC.addCell(createCellNB("12.2.2019-14.2.2019"));
	    tableC.addCell(createCellNB("3"));
	    tableC.addCell(createCellNB("1200"));

	    tableC.addCell(x2);										
	    tableC.addCell(x2);							
	    tableC.addCell(x2);								
	    tableC.addCell(x2);
	    
        document.add(new Paragraph("Majoitusraportti"));
        document.add(new Paragraph("Kuopio, Joensuu ja Jyväskylä 12.2.2019-15.3.2019"));
        document.add(new Paragraph(" "));

	    
        document.add(new Paragraph("Kuopio"));
        document.add(new Paragraph("Vuokratut mökit yhteensä: 1200€"));
	    document.add(tableC);

	    
	    document.add(new Paragraph("Joensuu"));
        document.add(new Paragraph("Vuokratut mökit yhteensä: 1200€"));
	    document.add(tableC);

	    
	    document.add(new Paragraph("Jyväskylä"));
        document.add(new Paragraph("Vuokratut mökit yhteensä: 1200€"));
	    document.add(tableC);



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
		Document document = new Document();

		try {
		updateCottageData(first_date, last_date, Locations);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("raportti_palvelut.pdf"));
        document.open();
        
	    PdfPCell x1 = createCellNB("");
	    PdfPCell x2 = createCellNB("");
	     
	    PdfPCell c1 = createCellNB("Palvelut");
	    PdfPCell c2 = createCellNB("Aikaväli");
	    PdfPCell c3 = createCellNB("Päivät");
	    PdfPCell c5 = createCellNB("Yhteensä €");


	    x1.setBorder(1);
	    x2.setBorder(2);

	    PdfPTable tableC = new PdfPTable(4); 					//Taulukko C
	    float[] columnsC = {8f, 12f, 8f, 8f};				//sarakkeiden jakaumat
	    tableC.setWidthPercentage(100);						//taulukon leveys
	    tableC.setSpacingAfter(30f); 							//paljonko väliä taulukon jälkeen
	    tableC.setSpacingBefore(20f);
	    tableC.setWidths(columnsC);							//lisätään sarakkaiden jaukama taulukkoon
	    tableC.addCell(x1);									//Lisätään solut taulukkoon									
	    tableC.addCell(x1);							
	    tableC.addCell(x1);
	    tableC.addCell(x1);
	    tableC.addCell(c1);							
	    tableC.addCell(c2);
	    tableC.addCell(c3);
	    tableC.addCell(c5);
	    tableC.addCell(createCellNB("Hieronta"));
	    tableC.addCell(createCellNB("12.2.2019-14.2.2019"));
	    tableC.addCell(createCellNB("3"));
	    tableC.addCell(createCellNB("1200"));

	    tableC.addCell(x2);										
	    tableC.addCell(x2);							
	    tableC.addCell(x2);								
	    tableC.addCell(x2);
	    
        document.add(new Paragraph("Palveluraportti"));
        document.add(new Paragraph("Kuopio, Joensuu ja Jyväskylä 12.2.2019-15.3.2019"));
        document.add(new Paragraph(" "));

	    
        document.add(new Paragraph("Kuopio"));
        document.add(new Paragraph("Ostetut palvelut yhteensä: 1200€"));
	    document.add(tableC);

	    
	    document.add(new Paragraph("Joensuu"));
        document.add(new Paragraph("Ostetut palvelut yhteensä: 1200€"));
	    document.add(tableC);

	    
	    document.add(new Paragraph("Jyväskylä"));
        document.add(new Paragraph("Ostetut palvelut yhteensä: 1200€"));
	    document.add(tableC);



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
