package com.citas.controlador;

import com.citas.dao.CitaDAO;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/citas")
public class CitaServlet    {

    private CitaDAO citaDAO = new CitaDAO();

    @PostMapping("/agendar")
    public Map<String, Object> agendarCita(@RequestParam String nombre, @RequestParam String fecha) {
        Map<String, Object> response = new HashMap<>();
        try {
            LocalDateTime fechaHora = LocalDateTime.parse(fecha);
            if (citaDAO.horarioDisponible(fechaHora)) {
                boolean success = citaDAO.insertarCita(nombre, fechaHora);
                response.put("status", success ? "success" : "error");
                response.put("message", success ? "Cita agendada exitosamente" : "Error al agendar la cita");
            } else {
                response.put("status", "error");
                response.put("message", "Horario no disponible");
            }
        } catch (DateTimeParseException e) {
            response.put("status", "error");
            response.put("message", "Formato de fecha inválido. Use YYYY-MM-DDTHH:MM:SS");
        }

        catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error inesperado: " + e.getMessage());
        }
        return response;
    }
}
