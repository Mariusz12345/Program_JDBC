package program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class JDBC {
	private final static String url = "jdbc:postgresql://localhost:5432/test3";
	private final static String userBazyDanych = "postgres";
	private final static String userHaslo = "maniek1";
	private final static String driver = "org.postgresql.Driver";

	public static void main(String[] args) throws Exception {
		// LADOWANIE STEROWNIKA

		Connection conn = null;
		// Zapytania do bazy danych za pomoca "Statement"
		Statement s = null;
		System.out.print("Sprawdzanie sterownika:");
		try {
			Class.forName(driver).newInstance(); // Zaladowanie sterownika do
													// systemu
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" sterownik OK");

		// laczenie z baza danych
		System.out.print("\nLaczenie z baza danych:");

		try {
			conn = DriverManager.getConnection(url, userBazyDanych, userHaslo); // polaczenie z baza danych

		} catch (SQLException e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" polaczenie OK\n");

		// ~~~~~~~~~~~~~~~ Dodanie do bazy danych ~~~~~~~~~~~~
		
		// ustawienie "connetion" na auto zeby moc samemu zatwierdzac transakcje
		conn.setAutoCommit(false);
		s = conn.createStatement();

		// String createTableFaktury = "CREATE TABLE faktury(id_faktury INTEGER
		// PRIMARY KEY,nazwaFaktury VARCHAR )";
		String zapytanie = "INSERT INTO klient (imie, nazwisko) VALUES ('l', 'l')";
		String zapytanie2 = "INSERT INTO faktury (id_faktury,nazwafaktury) VALUES ('19','nazwa4')";
		s.execute(zapytanie);
		s.execute(zapytanie2);
		conn.commit();

		// ~~~~~~~~~~~~~~ Koniec dodania ~~~~~~~~~~~~~~~~~~~

		// Pobieranie danych
		
		// Uzycie tranzakcji UNCOMITTED pozwalajaca na odczyt danych przed wywolaniem metody commit
		conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
		System.out.println("Pobieranie danych z bazy:");
		s = null;
		try {
			s = conn.createStatement(); // tworzenie obiektu Statement przesylajacego zapytania do bazy conn
			ResultSet r;
			r = s.executeQuery("Select * from klient;"); // wykonanie kwerendy i  przeslanie wynikow do obiektu ResultSet
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
		
		// Koniec pobierania
		
		
		// rollback i savepoint
		
		s = conn.createStatement();
		
		Savepoint save1 = conn.setSavepoint("INSERT_INTO");
		String zapytanie3 = "INSERT INTO klient (imie,nazwisko) VALUES ('e','e')";
		s.executeUpdate(zapytanie3);
		Savepoint save2 = conn.setSavepoint("INSERT_INTO2");
		String zapytanie4 = "INSERT INTO faktury(id_faktury,nazwafaktury) VALUES ('10','poli')";
		s.executeUpdate(zapytanie4);
		conn.rollback(save2);
		
		conn.commit();
		
		// koniec rollback
		

		// ZAMYKANIE POLACZENIA Z BAZA

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