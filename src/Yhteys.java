import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;

public class Yhteys {
	
	/**
	 * Tämä metodi tekee päivityksen tietokantaan
	 * @param sql Haluttu SQL-lause
	 */
	public void Paivita(String sql) {
	
	try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mokki?useTimezone=true&serverTimezone=UTC", "root", "Villi35Sika");
		
		Statement myStmt = myConn.createStatement();
		
		myStmt.executeUpdate(sql);
		}
	
	catch(Exception exc) {
		exc.printStackTrace();
		} 
		
	}
	
	/**
	 * Tämä metodi tuo halutun tulosteen tietokannasta
	 * @param sql Haluttu SQL-lause
	 * @return Tuloste ResultSet-muodossa
	 */
	public ResultSet TuoLista(String sql) {
		
		ResultSet holder = null;
		
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mokki?useTimezone=true&serverTimezone=UTC", "root", "Villi35Sika");
			
			Statement myStmt = myConn.createStatement();
			
			ResultSet myRs = myStmt.executeQuery(sql);
			
			holder = myRs;
			}
		
		catch(Exception exc) {
			exc.printStackTrace(); 
			
		}
		
		return holder;
}
}
	

