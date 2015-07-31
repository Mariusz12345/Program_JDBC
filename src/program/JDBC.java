package program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

public class JDBC {
	
	

	public static void main(String[] args) {
		// LADOWANIE STEROWNIKA
				System.out.print("Sprawdzanie sterownika:");
		        try {
					Class.forName("org.postgresql.Driver").newInstance();
				} catch (Exception e) {
					System.out.println("Blad przy ladowaniu sterownika bazy!");
					System.exit(1);
				}
				System.out.print(" sterownik OK");
				
				// LACZENIE Z BAZA
				System.out.print("\nLaczenie z baza danych:");
				String baza = "jdbc:postgresql://localhost:5432/test";
				
				String user = "postgres";
				String pass = "maniek1";
				java.sql.Connection conn = null;
		        try {
					conn=DriverManager.getConnection(baza, user, pass);
					
				} catch (SQLException e) {
					System.out.println("Blad przy ladowaniu sterownika bazy!");
					System.exit(1);
				}
				System.out.print(" polaczenie OK\n");
				
				

}
}