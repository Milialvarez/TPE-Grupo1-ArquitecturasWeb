package main.java.org.example.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
public class Alumno_Carrera implements Serializable{

    @EmbeddedId
    private Alumno_Carrera_Id id;

    @ManyToOne (fetch = FetchType.LAZY)
    @MapsId("alumno_id")
    @JoinColumn(name ="id_alumno", referencedColumnName = "alumno_id")
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("carrera_id")
    @JoinColumn(name="id_carrera", referencedColumnName = "carrera_id")
    private Carrera carrera;

    @Column
    private int antiguedad;
    @Column
    private boolean graduado;

    public Alumno_Carrera(Alumno alumno, Carrera carrera, int antiguedad, boolean graduado) {
        this.id = new Alumno_Carrera_Id();
        this.alumno = alumno;
        this.carrera = carrera;
        this.antiguedad = antiguedad;
        this.graduado = graduado;
    }

    public Alumno_Carrera() {
    }
}
