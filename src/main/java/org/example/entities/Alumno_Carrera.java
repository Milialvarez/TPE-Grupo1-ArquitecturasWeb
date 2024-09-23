package main.java.org.example.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Alumno_Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne (fetch = FetchType.EAGER)
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.EAGER)
    private Carrera carrera;

    @Column
    private int antiguedad;
    @Column
    private boolean graduado;

    public Alumno_Carrera(Alumno alumno, Carrera carrera, int antiguedad, boolean graduado) {
        this.alumno = alumno;
        this.carrera = carrera;
        this.antiguedad = antiguedad;
        this.graduado = graduado;
    }

    public Alumno_Carrera() {
    }
}
