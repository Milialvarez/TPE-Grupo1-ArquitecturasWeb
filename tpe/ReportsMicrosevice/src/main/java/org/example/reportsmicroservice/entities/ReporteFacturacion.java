package org.example.reportsmicroservice.entities;

import java.time.LocalDate;
import java.util.Date;


public class ReporteFacturacion{
    private String nombre;
    private String descripcion;
    private double totalFacturado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public ReporteFacturacion(String nombre, String descripcion, double totalFacturado, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.totalFacturado = totalFacturado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Nombre del reporte: " + this.nombre + " Descripcion: " + this.descripcion +
                " Total facturado: " + this.totalFacturado + " Desde: " + this.fechaInicio + " Hasta: " + this.fechaFin;
    }

    public double getTotalFacturado() {
        return totalFacturado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
}
