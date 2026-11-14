package Datos;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    String URL = "jdbc:mysql://localhost:3306/clinvet"; // Cambiar de ser necesario
    String USER = "root";
    String PASSWORD = "";

    public Conexion() {
    }

    public Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage() + " ERROR" + JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
}
