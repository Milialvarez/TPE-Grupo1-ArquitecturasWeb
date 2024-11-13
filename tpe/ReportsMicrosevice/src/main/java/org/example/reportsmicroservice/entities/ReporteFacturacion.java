package org.example.reportsmicroservice.entities;

import java.time.LocalDate;


public class ReporteFacturacion extends Reporte{
    private double totalFacturado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public ReporteFacturacion(String nombre, String descripcion, double totalFacturado, LocalDate fechaInicio, LocalDate fechaFin) {
        super(nombre, descripcion);
        this.totalFacturado = totalFacturado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Nombre del reporte: " + super.getNombre() + " Descripcion: " + super.getDescripcion() +
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