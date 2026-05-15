package com.citas.controlador;

import com.citas.dao.CitaDAO;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")

@WebServlet("/api/citas")
public class CitaServlet extends HttpServlet {
    private CitaDAO dao = new CitaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. Configuración de cabeceras (CORS y JSON)
        response.setHeader("Access-Control-Allow-Origin", "*"); 
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String nombre = request.getParameter("nombre");
        String fechaStr = request.getParameter("fecha"); 
        JsonObject jsonResponse = new JsonObject();

        try {
            // Validación de datos de entrada
            if (nombre == null || fechaStr == null || nombre.trim().isEmpty()) {
                throw new Exception("Faltan datos obligatorios (nombre o fecha)");
            }

            // Conversión de fecha (El HTML datetime-local envía yyyy-MM-ddTHH:mm)
            LocalDateTime fecha;
            try {
                fecha = LocalDateTime.parse(fechaStr);
            } catch (DateTimeParseException e) {
                throw new Exception("Formato de fecha inválido");
            }

            // Lógica de negocio mediante el DAO
            if (dao.horarioDisponible(fecha)) {
                boolean exito = dao.insertarCita(nombre, fecha);
                
                if (exito) {
                    jsonResponse.addProperty("success", true);
                    jsonResponse.addProperty("message", "¡Cita agendada con éxito para " + nombre + "!");
                } else {
                    jsonResponse.addProperty("success", false);
                    jsonResponse.addProperty("message", "Error técnico al guardar en la base de datos");
                }
            } else {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Lo sentimos, el horario ya está ocupado");
            }

        } catch (Exception e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Error: " + e.getMessage());
            // Imprime el error en la consola de VS Code para que puedas debuguear
            e.printStackTrace(); 
        }

        // Envío de la respuesta al JavaScript
        response.getWriter().write(jsonResponse.toString());
    }

    // Soporte para peticiones OPTIONS (necesario para algunos navegadores con CORS)
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}