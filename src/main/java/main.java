package main.java;

import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Carrera;
import main.java.org.example.helpers.tableDataPop;
import main.java.org.example.repositories.AlumnoCarreraRepositoryImpl;
import main.java.org.example.repositories.AlumnoRepositoryImpl;
import main.java.org.example.repositories.CarreraRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class main {
    public static void main(String[] args) {
        System.out.println("1) Generando esquema en la base de datos: ");
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = enf.createEntityManager();

        AlumnoRepositoryImpl alumnoRep = new AlumnoRepositoryImpl(em);
        CarreraRepositoryImpl carreraRep = new CarreraRepositoryImpl(em);
        AlumnoCarreraRepositoryImpl alumnoCarreraRep = new AlumnoCarreraRepositoryImpl(em);

        System.out.println("Populando tablas con algunos datos");
        tableDataPop.poblarTablaAlumnos(alumnoRep);
        tableDataPop.poblarTablaCarreras(carreraRep);

        // Implementación de consultas
        // 2) A. Dar de alta un estudiante
        System.out.println("2) A / DAR DE ALTA UN ESTUDIANTE: Juan Domingo Peron");
        Alumno miGeneralQueGrandeSos = new Alumno("Juan Domingo", "Peron", 129, "masculino", 140578, "lobos",
                17101945);
        alumnoRep.crearAlumno(miGeneralQueGrandeSos);
        System.out.println(alumnoRep.getAlumnoByNroLibreta(17101945));
        System.out.println("---------------");

        // 2) B. Matricular un estudiante en una carrera
        System.out.println("2) B / MATRICULAR UN ESTUDIANTE EN UNA CARRERA: Juan Domingo Peron en Economicas");
        Carrera carrera = carreraRep.buscarCarreraPorNombre("economicas");
        alumnoCarreraRep.matricularAlumno(miGeneralQueGrandeSos, carrera);
        System.out.println(alumnoCarreraRep.buscarAlumnoID(miGeneralQueGrandeSos.getAlumno_id()));
        System.out.println("---------------");

//        System.out.print(alumno.getAlumnoById(new Long("9")));
//        alumno.saveAlumno(Manuel);
//
//        System.out.print(alumno.updateAlumno(prueba, new Long("13")));
//
//        List<Alumno> alumnos = alumno.getAlumnos("nombre", false);
//        for (Alumno a : alumnos) {
//            System.out.println(a);
//        }
//
//        System.out.println("2) D/ ");
//        System.out.print(alumno.getAlumnoByNroLibreta(1));
//        System.out.println("2) E/ ");
//        System.out.println(alumno.getAlumnosByGenero("femenino"));

//        em.persist(Mili);
//        em.persist(mT);
        em.close();
    }
}