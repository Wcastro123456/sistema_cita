package com.citas.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Cambia 'sistema_citas' por el nombre exacto de tu BD en MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_citas";
    private static final String USER = "root"; // usuario de MySQL
    private static final String PASS = "root"; // contraseña de MySQL

    public static Connection getConexion() {
        Connection cn = null;
        try {
            // Carga el driver que descargamos en el pom.xml
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Intenta conectar
            cn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✅ Conexión Exitosa");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error: No se encontró el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Error: Falló la conexión. Revisa URL, usuario o clave");
            e.printStackTrace();
        }
        return cn;
    }
}