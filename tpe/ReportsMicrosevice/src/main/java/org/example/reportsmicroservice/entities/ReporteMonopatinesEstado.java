package org.example.reportsmicroservice.entities;

public class ReporteMonopatinesEstado extends Reporte{
    private Integer cantActivos;
    private Integer cantMantenimiento;

    public ReporteMonopatinesEstado(Integer a, Integer m){
        super("Reporte de estado de monopatines", "El reporte provee una comparacion de la cantidad de monopatines activos versus la cantidad en mantenimiento");
        this.cantActivos = a;
        this.cantMantenimiento = m;
    }

    @Override
    public String toString() {
        return "Nombre del reporte: " + super.getNombre() + "Descripcion: " + super.getDescripcion() + "Cantidad de monopatines activos: " + this.cantActivos.toString() +"Cantidad de monopatines en mantenimiento: " + this.cantMantenimiento.toString();
    }
}
