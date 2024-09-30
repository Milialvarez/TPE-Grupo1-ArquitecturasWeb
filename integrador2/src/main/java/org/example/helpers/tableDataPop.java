package main.java.org.example.helpers;

import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Carrera;
import main.java.org.example.repositories.AlumnoCarreraRepositoryImpl;
import main.java.org.example.repositories.AlumnoRepositoryImpl;
import main.java.org.example.repositories.CarreraRepositoryImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private static Carrera getRandomMajor(int n, List<Carrera> carreras){
        Random r = new Random();
        return carreras.get((r.nextInt(n)));
    }

    public static void matricularEstudiantes(AlumnoCarreraRepositoryImpl acRep, AlumnoRepositoryImpl alRep, CarreraRepositoryImpl carRep){
            for (Alumno al : alRep.getAlumnos("nombre", false)){
                acRep.matricularAlumno(al, getRandomMajor(5, carRep.listarCarreras()));
            }
    }
}
