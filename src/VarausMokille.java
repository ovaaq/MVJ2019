public class VarausMokille {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	Toimipiste toimipiste = new Toimipiste();
	
	toimipiste.lisaa("moi", "moi", "moi", "moi", "moi", "moi");
	toimipiste.paivita("moi", "moi", "moi", "moi", "moi", "moi", "11");
	toimipiste.poista("11");
	toimipiste.otaTiedot("10");
	System.out.println(toimipiste.getM_postinumero());
	}

}
