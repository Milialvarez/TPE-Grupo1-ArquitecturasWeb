package main.java.org.example.entities;

import javax.persistence.*;

@Entity
public class Alumno_Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    @Column
    private int antiguedad;
    @Column
    private boolean graduado;


}
