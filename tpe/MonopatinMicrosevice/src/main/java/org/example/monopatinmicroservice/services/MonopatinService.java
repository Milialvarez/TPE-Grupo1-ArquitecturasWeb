package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.repositories.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepository monopatinRepository;

    public List<Monopatin> getAll() {
        return monopatinRepository.findAll();
    }

    public Monopatin add(Monopatin monopatin) {
        return monopatinRepository.save(monopatin);
    }

    public Monopatin getById(Long id) {
        return monopatinRepository.findById(id).orElse(null);
    }

    public List<Monopatin> getMonopatinesPorViajesPorAnio(Integer anio, Integer xViajes) {
        return monopatinRepository.getMonopatinesPorViajesPorAnio(anio, xViajes);
    }

    public Monopatin delete(Long id) {
        Monopatin monopatin = monopatinRepository.findById(id).orElse(null);

        if (monopatin != null) {
            monopatinRepository.deleteById(id);
        }

        return monopatin;
    }

    public ArrayList<Monopatin> getClosestMonopatins(int posx, int posy) {
        double radio = 1.0; //radio de maximo 1 kilómetro
        List<Monopatin> monopatins = this.monopatinRepository.findAll();
        ArrayList<Monopatin> response = new ArrayList<>();
        for(Monopatin monopatin : monopatins) {
            if(this.calcularDistancia(monopatin, posx, posy) <= radio) {
                response.add(monopatin);
            }
        }
        return response;
    }

    public double calcularDistancia(Monopatin m, int x, int y) {
        return Math.sqrt(Math.pow(x - m.getPosX(), 2) + Math.pow(y - m.getPosY(), 2));
    }


}
