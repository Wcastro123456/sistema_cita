package com.citas.modelo;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String clienteNombre;
    private LocalDateTime fechaHora;
    private int servicioId;

    // Constructor vacío
    public Cita() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public int getServicioId() { return servicioId; }
    public void setServicioId(int servicioId) { this.servicioId = servicioId; }
}