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
		Statement s1 = null;
		Statement s2 = null;
		
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
		
	
		s1 = f.dodanieFaktury(conn, s1);
		s2 = k.dodanieKlienta(conn, s2);
		f.wypisFaktury(conn, s1);
		k.wypisKlientow(conn, s2);
		
		
		
		conn.commit();

		

		// Zamykanie polaczenia z baza danych

		System.out.print("\nZamykanie polaczenia z baza:");
		try {
			s1.close();
			s2.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Blad przy zamykaniu polaczenia " + e.toString());
			System.exit(4);
		}
		System.out.print(" zamkniecie OK");

	}

}