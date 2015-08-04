package program;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FakturaMenadzer   {
	
	
	public static Statement dodanieFaktury(Connection conn , Statement s) throws SQLException{
		
		s = conn.createStatement();
		conn.setAutoCommit(false);
		
		String zapytanie = "INSERT INTO faktury(id_faktury,nazwafaktury)VALUES('111','ascsek')";
		s.execute(zapytanie);

		conn.commit();
		
		return s;
		
	}

}
