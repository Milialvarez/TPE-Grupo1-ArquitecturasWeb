package main.java.org.example.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Setter
@Getter
@Embeddable
public class Alumno_Carrera_Id implements java.io.Serializable {
    private static final long serialVersionUID = 3255599046800011076L;

    @Column
    private Long alumno_id;

    @Column
    private Long carrera_id;

    public Alumno_Carrera_Id() {

    }

    public Alumno_Carrera_Id(Long a_id, Long c_id) {
        alumno_id = a_id;
        carrera_id = c_id;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        Alumno_Carrera_Id that = (Alumno_Carrera_Id) o;
        return alumno_id == that.alumno_id && carrera_id == that.carrera_id;
    }


}
