package main.java.org.example.entities;

import main.java.org.example.entities.Carrera;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    private String genero;
    @Column
    private int dni;
    @Column
    private String ciudad;
    @Column
    private int nro_libreta;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ArrayList<Carrera> carreras;
}
