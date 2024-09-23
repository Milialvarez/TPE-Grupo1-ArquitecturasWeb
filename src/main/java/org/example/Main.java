package main.java.org.example;

import main.java.org.example.entities.Alumno;
import main.java.org.example.entities.Alumno_Carrera;
import main.java.org.example.entities.Carrera;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = enf.createEntityManager();
        em.getTransaction().begin();
        Alumno facu = new Alumno("Facundo", "Bravo", 19, "Masculino", 46814650,"Tandil", 45363377);
        em.persist(facu);
        Carrera TUDAI = new Carrera("TUDAI", 2);
        em.persist(TUDAI);
        Alumno_Carrera fT = new Alumno_Carrera(facu, TUDAI, 2, false);
        em.persist(fT);
        em.getTransaction().commit();
        em.close();
    }
}