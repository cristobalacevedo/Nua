package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:data/inmobiliaria.db"; // SQLite database file path
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to DB has been established."); // SPANISH: "Conexión a SQLite establecida."")
            //return True;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

	public static Connection getConnection() {
		Connection conn = connect();
		if (conn == null) {
			System.out.println("Error al conectar a la base de datos."); // SPANISH
		}
		return conn;
	}
}	