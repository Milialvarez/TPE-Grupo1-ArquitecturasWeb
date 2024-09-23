package main.java.org.example.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carrera_id;
    @Column
    private String nombre;
    @Column
    private int anios;
    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
    private List<Alumno_Carrera> alumnos;

    public Carrera(String nombre, int anios) {
        super();
        this.nombre = nombre;
        this.anios = anios;
        this.alumnos = new ArrayList<>();
    }

    public Carrera() {
    }
}
