package program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

public class JDBC {
	private final static String url = "jdbc:postgresql://localhost:5432/test";
	private final static String userBazyDanych = "postgres";
	private final static String userHaslo = "maniek1";
	private final static String driver = "org.postgresql.Driver";

	public static void main(String[] args) {
		// LADOWANIE STEROWNIKA

		Connection conn = null;
		Statement s = null;
		
		System.out.print("Sprawdzanie sterownika:");
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" sterownik OK");

		// LACZENIE Z BAZA
		System.out.print("\nLaczenie z baza danych:");

		try {
			conn = DriverManager.getConnection(url, userBazyDanych, userHaslo);

		} catch (SQLException e) {
			System.out.println("Blad przy ladowaniu sterownika bazy!");
			System.exit(1);
		}
		System.out.print(" polaczenie OK\n");
		
		
		// ZAMYKANIE POLACZENIA Z BAZA
				System.out.print("\nZamykanie polaczenia z baza:");
				try {
					s.close();
					conn.close();
				} catch (SQLException e) {
					System.out.println("Blad przy zamykaniu polaczenia " +e.toString());
					System.exit(4);
				}
				System.out.print(" zamkniecie OK");

	}
}