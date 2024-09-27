package main.java.org.example.repositories;

import main.java.org.example.entities.Carrera;

import java.util.List;

public interface CarreraRepository {

    public void crearCarrera(Carrera c);
    public List<Carrera> listarCarreras();
    public Carrera buscarCarreraPorNombre(String c);
}
