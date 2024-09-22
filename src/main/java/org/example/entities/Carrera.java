package main.java.org.example.entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String nombre;
    @Column
    private int anios;
    @ManyToMany(mappedBy = "carreras", fetch = FetchType.LAZY)
    private ArrayList<Alumno> alumnos;
}
