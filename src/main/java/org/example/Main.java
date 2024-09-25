package main.java.org.example;

import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Alumno_Carrera;
import main.java.org.example.entities.Carrera;
import main.java.org.example.repositories.AlumnoRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = enf.createEntityManager();
        em.getTransaction().begin();
//        Alumno facu = new Alumno("Facundo", "Bravo", 19, "Masculino", 46814650,"Tandil", 45363377);
//        em.persist(facu);
//        Carrera TUDAI = new Carrera("TUDAI", 2);
//        em.persist(TUDAI);
//        Alumno_Carrera fT = new Alumno_Carrera(facu, TUDAI, 2, false);
//        em.persist(fT);
//        Alumno Mili = new Alumno("Milagros", "Alvarez", 19, "Femenino", 46555088, "Tandil",26362727);
//        Alumno_Carrera mT = new Alumno_Carrera(Mili, TUDAI, 2, false);
//

//        Alumno Manuel = new Alumno("Manuel", "Alvarez", 30, "Masculino", 35789654, "Tandil", 36772);
        AlumnoRepositoryImpl alumno = new AlumnoRepositoryImpl(em);
//        Alumno maca = new Alumno("Maca", "Alvarez", 16, "Femenino", 48555088, "Tandil",636336);
//        Alumno Mili = new Alumno("Milagros", "Alvarez", 19, "Femenino", 46555088, "Tandil",26362727);
//        em.persist(maca);
//        em.persist(Manuel);
//        em.persist(Mili);

        Alumno prueba = new Alumno("prueba", "pruebaa", 1, "masc", 366363, "t", 773);


        //System.out.print(alumno.getAlumnoById(new Long("9")));
        //alumno.saveAlumno(Manuel);

        System.out.print(alumno.updateAlumno(prueba, new Long("13")));

//        em.persist(Mili);
//        em.persist(mT);
        em.getTransaction().commit();
        em.close();
    }
}