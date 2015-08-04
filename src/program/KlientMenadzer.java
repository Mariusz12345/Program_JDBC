package program;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class KlientMenadzer {

	public  Statement dodanieKlienta(Connection conn, Statement s) throws SQLException {

		s = conn.createStatement();
		conn.setAutoCommit(false);

		String zapytanie = "INSERT INTO klient (imie,nazwisko)VALUES('c','c')";
		s.execute(zapytanie);
		
		return s;
	}
	
	public  void wypisKlientow(Connection conn , Statement s) throws SQLException{
		s = conn.createStatement();
		System.out.println("Pobieranie danych z bazy:");
		s = null;
		try {
			s = conn.createStatement(); // tworzenie obiektu Statement
										// przesylajacego zapytania do bazy conn
			ResultSet r;
			r = s.executeQuery("Select * from klient;"); // wykonanie kwerendy i przeslanianie wynikow do obiektu ResultSet
														
			r.next(); // przejscie do kolejnego rekordu (wiersza) otrzymanych wynikow

			ResultSetMetaData rsmd = r.getMetaData();
			int iloscikolumn = rsmd.getColumnCount(); // pobieranie liczby kolumn

			// wyswietlanie nazw kolumn:
			for (int i = 1; i <= iloscikolumn; i++) {
				System.out.print(rsmd.getColumnLabel(i) + "  |  ");
			}
			System.out.print("\n------------------------------------\n");

			// wyswietlanie kolejnych rekordow:
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
