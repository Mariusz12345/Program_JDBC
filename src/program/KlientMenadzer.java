package program;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class KlientMenadzer {

	public static Statement dodanieKlienta(Connection conn, Statement s) throws SQLException {

		s = conn.createStatement();
		conn.setAutoCommit(false);

		String zapytanie = "INSERT INTO klient (imie,nazwisko)VALUES('a','c')";
		s.execute(zapytanie);

		conn.commit();
		return s;
	}

}
