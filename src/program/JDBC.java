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
		// LADOWANIE STEROWNIKA
		
		Connection connetion = null;
		//Zapytania do bazy danych za pomoca "Statement"
		Statement s = null;
		System.out.print("Sprawdzanie sterownika:");
		try {
			Class.forName(driver).newInstance(); // Zaladowanie sterownika do systemu
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" sterownik OK");

		// laczenie z baza danych
		System.out.print("\nLaczenie z baza danych:");

		try {
			connetion = DriverManager.getConnection(url, userBazyDanych, userHaslo); // polaczenie z baza danych

		} catch (SQLException e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" polaczenie OK\n");

		
		//~~~~~~~~~~~~~~~ Dodanie do bazy danych ~~~~~~~~~~~~
		connetion.setAutoCommit(false);
		s = connetion.createStatement();
		connetion.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		
		//String createTableFaktury = "CREATE TABLE faktury(id_faktury INTEGER PRIMARY KEY,nazwaFaktury VARCHAR )";
		String zapytanie = "INSERT INTO klient (imie, nazwisko) VALUES ('p', 'p')";
		s.execute(zapytanie);
		connetion.commit();
		
		//~~~~~~~~~~~~~~ Koniec dodania ~~~~~~~~~~~~~~~~~~~
	
		// ZAMYKANIE POLACZENIA Z BAZA

		System.out.print("\nZamykanie polaczenia z baza:");
		try {
			s.close();
			connetion.close();
		} catch (SQLException e) {
			System.out.println("Blad przy zamykaniu polaczenia " + e.toString());
			System.exit(4);
		}
		System.out.print(" zamkniecie OK");

	}
}