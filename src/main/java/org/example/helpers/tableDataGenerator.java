package main.java.org.example.helpers;

import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Carrera;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class tableDataGenerator {
    public static List<Alumno> crearDatosAlumno() {
        Alumno a1 = new Alumno("Manuel", "Alvarez", 30, "masculino", 37766544, "tandil",23980);
        Alumno a2 = new Alumno("Milagros Irma", "Alvarez", 19, "femenino", 46001923, "cuzco", 66756);
        Alumno a3 = new Alumno("Facundo", "Bravo", 19, "masculino", 45801635, "tandil", 91218);
        Alumno a4 = new Alumno("Carolina", "Vytas Tuckus", 22, "femenino", 42768998, "capital federal", 26611);

        List<Alumno> l = new ArrayList<Alumno>();

        l.add(a1);
        l.add(a2);
        l.add(a3);
        l.add(a4);

        return l;
    }

    public static List<Carrera> crearDatosCarrera() {
        Carrera c1 = new Carrera("tudai");
        Carrera c2 = new Carrera("sistemas");
        Carrera c3 = new Carrera("economicas");

        List<Carrera> l = new ArrayList<Carrera>();

        l.add(c1);
        l.add(c2);
        l.add(c3);

        return l;
    }
}
