package org.example.reportsmicroservice.entities;


public abstract class Reporte {
    private String nombre;
    private String descripcion;

    public Reporte(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Reporte() {

    }

    public abstract String toString ();

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}


