package main.java.org.example.helpers;

import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Carrera;
import main.java.org.example.repositories.AlumnoRepositoryImpl;
import main.java.org.example.repositories.CarreraRepositoryImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class tableDataPop {

    public static void poblarTablaAlumnos(AlumnoRepositoryImpl alRep) {
        List<Alumno> l = new ArrayList<Alumno>(tableDataGenerator.crearDatosAlumno());

        for (Alumno alumno : l) {
            alRep.crearAlumno(alumno);
        }
    }

    public static void poblarTablaCarreras(CarreraRepositoryImpl carRep) {
        List<Carrera> l = new ArrayList<Carrera>(tableDataGenerator.crearDatosCarrera());

        for (Carrera carrera : l) {
            carRep.crearCarrera(carrera);
        }
    }
}
