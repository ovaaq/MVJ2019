package pdftest;

import java.util.ArrayList;
import java.util.List;

public class Report {
	String first_date = "";
	String last_date = "";
	List Location = new ArrayList();

	public List getReportCottage() {
		//Tämä metodi palauttaa listan joka pitää sisällään raportin majoituksista
		//Määrä, palvelu, hinta, toimipiste
		List dummy = new ArrayList();
		return dummy;
	}
	
	public List getReportService() {
		//Tämä metodi palauttaa listan joka pitää sisällään raportin palveluista
		//Määrä, mökki_id, hinta, toimipiste
		List dummy = new ArrayList();
		return dummy;
	}
}
