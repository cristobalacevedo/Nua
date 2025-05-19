package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection conectar() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:data/inmobiliaria.db"; // Nombre del archivo SQLite
            conn = DriverManager.getConnection(url);
            System.out.println("Conexión exitosa a SQLite.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}