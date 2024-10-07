package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Carrera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carrera_id;
    @Column
    private String nombre;
    @Column
    private int anios;
//    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
//    private List<Alumno_Carrera> alumnos;

    public Carrera(String nombre, int anios) {
        super();
        this.nombre = nombre;
        this.anios = anios;
//        this.alumnos = new ArrayList<>();
    }

    public Carrera(String nombre) {
        super();
        this.nombre = nombre;
        this.anios = 5; //duracion por defecto
//        this.alumnos = new ArrayList<>();
    }

    public Carrera() {

    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id: " + this.carrera_id +
                ", nombre: '" + nombre + '\'' +
                '}';
    }
}
