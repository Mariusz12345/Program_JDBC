package program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	private final static String url = "jdbc:postgresql://localhost:5432/test3";
	private final static String userBazyDanych = "postgres";
	private final static String userHaslo = "maniek1";
	private final static String driver = "org.postgresql.Driver";

	public static void main(String[] args) throws Exception {
		
		

		Connection conn = null;
		Statement s = null;
		
		// ~~~~~~~~~~~~~~~~~~~~~~~ Polaczenie z baza danych ~~~~~~~~~~~~~~~~~~~~~~~// 
		
		System.out.print("Sprawdzanie sterownika:");
		try {
			Class.forName(driver).newInstance(); // Zaladowanie sterownika do systemu
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" sterownik OK");

		
		System.out.print("\nLaczenie z baza danych:");

		try {
			conn = DriverManager.getConnection(url, userBazyDanych, userHaslo); 
		} catch (SQLException e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" polaczenie OK\n");

		// ~~~~~~~~~~~~~~~~~~~~~~~ Koniec polaczenia ~~~~~~~~~~~~~~~~~~~~~~~//
		
		
		
		FakturaMenadzer f = new FakturaMenadzer();
		KlientMenadzer k = new KlientMenadzer();
		
		s = f.dodanieFaktury(conn, s);
		s = k.dodanieKlienta(conn, s);
		f.wypisFaktury(conn, s);
		k.wypisKlientow(conn, s);

		
		


		// Zamykanie polaczenia z baza danych

		System.out.print("\nZamykanie polaczenia z baza:");
		try {
			s.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Blad przy zamykaniu polaczenia " + e.toString());
			System.exit(4);
		}
		System.out.print(" zamkniecie OK");

	}

}