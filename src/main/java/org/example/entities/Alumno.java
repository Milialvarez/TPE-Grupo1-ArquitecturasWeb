package main.java.org.example.entities;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Alumno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long alumno_id;
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
    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<Alumno_Carrera> carreras;

    public Alumno(String nombre, String apellido, int edad, String genero, int dni, String ciudad, int nro_libreta) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad = ciudad;
        this.nro_libreta = nro_libreta;
        carreras = new ArrayList<>();
    }

    public Alumno() {
    }

    public Long getAlumno_id() {
        return alumno_id;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "alumno_id=" + alumno_id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }

}
