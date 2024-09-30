package main.java;

import main.java.org.example.dtos.ReporteCarrerasDTO;
import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Carrera;
import main.java.org.example.helpers.tableDataPop;
import main.java.org.example.repositories.AlumnoCarreraRepositoryImpl;
import main.java.org.example.repositories.AlumnoRepositoryImpl;
import main.java.org.example.repositories.CarreraRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        System.out.println("1) Generando esquema en la base de datos: ");
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = enf.createEntityManager();
        System.out.println("---------------");

        AlumnoRepositoryImpl alumnoRep = new AlumnoRepositoryImpl(em);
        CarreraRepositoryImpl carreraRep = new CarreraRepositoryImpl(em);
        AlumnoCarreraRepositoryImpl alumnoCarreraRep = new AlumnoCarreraRepositoryImpl(em);

        System.out.println("Populando tablas con algunos datos");
        tableDataPop.poblarTablaAlumnos(alumnoRep);
        tableDataPop.poblarTablaCarreras(carreraRep);
        tableDataPop.matricularEstudiantes(alumnoCarreraRep, alumnoRep, carreraRep);
        System.out.println("---------------");

        // Implementación de consultas
        // 2) A. Dar de alta un estudiante
        System.out.println("2) A / DAR DE ALTA UN ESTUDIANTE: Nicolas Simonelli");
        Alumno alum007 = new Alumno("Nicolas", "Simonelli", 29, "masculino", 38499001, "olavarria",
                17101945);
        alumnoRep.crearAlumno(alum007);
        System.out.println(alumnoRep.getAlumnoByNroLibreta(17101945));
        System.out.println("---------------");

        // 2) B. Matricular un estudiante en una carrera
        System.out.println("2) B / MATRICULAR UN ESTUDIANTE EN UNA CARRERA: Nicolas Simonelli en bioquimica");
        Carrera carrera = carreraRep.buscarCarreraPorNombre("bioquimica");
        alumnoCarreraRep.matricularAlumno(alum007, carrera);
        System.out.println(alumnoCarreraRep.buscarAlumnoID(alum007.getAlumno_id()));
        System.out.println("---------------");

        //2) C. Recuperar todos los estudiantes y especificar algún criterio de ordenamiento simple.
        System.out.println("2) C / LISTAR TODOS LOS ALUMNOS");

        List<Alumno> alumnos = alumnoRep.getAlumnos("apellido", true);
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
        System.out.println("---------------");

        // 2) D. Recuperar un estudiante, en base a su número de libreta universitaria.
        System.out.println("2) D / ALUMNOS POR LU: 12345");
        Alumno al = alumnoRep.getAlumnoByNroLibreta(23980);
        System.out.println(al);
        System.out.println("---------------");

        //2) E. Recuperar todos los estudiantes, en base a su género.
        System.out.println("2) E / ALUMNOS POR GÉNERO: Fememenino");
        List<Alumno> alumnosGenero = alumnoRep.getAlumnosByGenero("femenino");
        for (Alumno a : alumnosGenero) {
            System.out.println(a);
        }
        System.out.println("---------------");

        // 2) F. Recuperar las carreras con estudiantes inscriptos y ordenar por cantidad de inscriptos.
        System.out.println("2) F / TODAS LAS CARRERAS CON ALUMNOS INSCRIPTOS");
        List<Carrera> carrerasEstInscriptos = carreraRep.listarCarrerasConAlumnosInscriptos();
        for (Carrera c : carrerasEstInscriptos) {
            System.out.println(c);
        }
        System.out.println("---------------");

        // 2) G. Recuperar los estudiantes de una determinada carrera filtrado por ciudad de residencia.
        System.out.println("2) G / TODOS LOS ESTUDIANTES DE LA CARRERA: Tudai Y CIUDAD: Tandil");
        Carrera c5 = carreraRep.buscarCarreraPorNombre("tudai");
        List<Alumno> listarAlumnosPorCarrera = alumnoRep.getAlumnosByMajorFilteredBy(c5, "tandil");
        for (Alumno a : listarAlumnosPorCarrera) {
            System.out.println(a);
        }
        System.out.println("---------------");


        // 3) Generar un reporte de las carreras, que para cada carrera incluya información de los
        //	  inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
        //	  los años de manera cronológica.

        System.out.println("3) REPORTE DE LAS CARRERAS POR NOMBRE Y EN ORDEN CRONOLOGICO");
        List<ReporteCarrerasDTO> majorsReport = carreraRep.getMajorsReport();
        for (ReporteCarrerasDTO m : majorsReport){
            System.out.println(m);
        }
        System.out.println("---------------");

        em.close();
    }
}