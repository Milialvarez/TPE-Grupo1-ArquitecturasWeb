package org.example.reportsmicroservice.entities;

import lombok.Getter;

@Getter
public class ReporteMonopatinesUso extends Reporte {
    private Long idMonop;
    private float maxKm;
    private float tiempoMax;
    private float tiempoMaxConPausas;

    public ReporteMonopatinesUso(Long id, float km, float t, float tP){
        super("Reporte de uso de monopatines", "El reporte provee una descripcion sobre el uso de monopatines en relacion al max de km recorridos, el tiempo de uso (con o sin pausa)");
        this.idMonop = id;
        this.maxKm = km;
        this.tiempoMax = t;
        this.tiempoMaxConPausas = tP;
    }

    @Override
    public String toString() {
        return "Nombre del reporte: " + super.getNombre() + "Descripcion: " + super.getDescripcion() + " Id del monopatin: " + this.idMonop + " Cantidad de km recorridos: " + this.maxKm + " Tiempo max de uso: " + this.tiempoMax + " Tiempo max de uso con pausas: " + this.tiempoMaxConPausas;
    }
}
