package main.java.entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int carrera_id;
    @Column
    private String nombre;
    @Column
    private int anios;
    @OneToMany(fetch = FetchType.LAZY)
    private ArrayList<Alumno_Carrera> alumnos;
}
