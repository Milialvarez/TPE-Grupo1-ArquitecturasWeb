package org.example.reportsmicroservice.entities;

import java.time.LocalDate;
import java.util.Date;


public class ReporteFacturacion extends Reporte{
    private double totalFacturado;
    private Date fechaInicio;
    private Date fechaFin;

    public ReporteFacturacion(String nombre, String descripcion, double totalFacturado, Date fechaInicio, Date fechaFin) {
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }
}
