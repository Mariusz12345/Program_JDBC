package program;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class FakturaMenadzer   {
	
	
	public  Statement dodanieFaktury(Connection conn , Statement s) throws SQLException{
		
		s = conn.createStatement();
		conn.setAutoCommit(false);
		
		String zapytanie = "INSERT INTO faktury(id_faktury,nazwafaktury)VALUES('8','jacek8')";
		s.execute(zapytanie);
		
		return s;
		
	}
	
	public  void wypisFaktury(Connection conn , Statement s) throws SQLException{
		
		conn.setAutoCommit(false);
		s = conn.createStatement();
		System.out.println("Pobieranie danych z bazy:");
		s = null;
		try {
			s = conn.createStatement(); // Tworzenie obiektu Statement
										// Przesylajacego zapytania do bazy conn
			ResultSet r;
			r = s.executeQuery("Select * from faktury;"); // Wykonanie kwerendy i przeslanianie wynikow do obiektu ResultSet
														
			r.next(); // Przejscie do kolejnego rekordu (wiersza) 

			ResultSetMetaData rsmd = r.getMetaData();
			int iloscikolumn = rsmd.getColumnCount(); // Pobieranie liczby kolumn

			//Wyswietlenie nazw kolumn
			for (int i = 1; i <= iloscikolumn; i++) { 
				System.out.print(rsmd.getColumnLabel(i) + "  |  ");
			}
			System.out.print("\n------------------------------------\n");

			// Wyswietlanie kolejnych rekordow
			while (r.next()) {
				for (int i = 1; i <= iloscikolumn; i++) {
					Object obj = r.getObject(i);
					if (obj != null)
						System.out.print(obj.toString() + " | ");
					else
						System.out.print(" ");
				}
				System.out.println();
			}
			
			
		} catch (SQLException e) {
			System.out.println("Blad odczytu z bazy! " + e.toString());
			System.exit(3);
		}
		
	}

}
