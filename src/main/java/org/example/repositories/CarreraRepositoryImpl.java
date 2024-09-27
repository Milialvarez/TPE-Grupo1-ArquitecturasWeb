package main.java.org.example.repositories;

import main.java.org.example.entities.Carrera;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarreraRepositoryImpl implements CarreraRepository {
    private EntityManager em;

    public CarreraRepositoryImpl(EntityManager e){
        this.em=e;
    }

    @Override
    public void crearCarrera(Carrera c) {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }


    public Carrera buscarCarreraPorNombre(String c) {
        TypedQuery<Carrera> query = em.createQuery("SELECT c " +
                        "FROM Carrera c " +
                        "WHERE c.nombre = :carreraBuscada"
                , Carrera.class);
        query.setParameter("carreraBuscada", c);

        Carrera carrera = query.getSingleResult();

        return carrera;
    }

    @Override
    public List<Carrera> listarCarreras() {
        TypedQuery<Carrera> query = em.createQuery(
                "SELECT c " +
                        "FROM Carrera c", Carrera.class);

        List<Carrera> carreras = query.getResultList();

        return carreras;
    }
}
