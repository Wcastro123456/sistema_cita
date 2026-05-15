package com.citas.dao;

import com.citas.proyecto.Conexion; 
import java.sql.*;
import java.time.LocalDateTime;

public class CitaDAO {
    
    public boolean horarioDisponible(LocalDateTime fecha) {
        String sql = "SELECT COUNT(*) FROM citas WHERE fecha_hora = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setTimestamp(1, Timestamp.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertarCita(String nombre, LocalDateTime fecha) {
        String sql = "INSERT INTO citas (cliente_nombre, fecha_hora, servicio_id) VALUES (?, ?, 1)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nombre);
            ps.setTimestamp(2, Timestamp.valueOf(fecha));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}