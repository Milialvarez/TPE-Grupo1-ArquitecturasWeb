package org.example.monopatinmicroservice.utils;

import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.entities.Parada;
import org.example.monopatinmicroservice.entities.Pausa;
import org.example.monopatinmicroservice.entities.Viaje;
import org.example.monopatinmicroservice.repositories.MonopatinRepository;
import org.example.monopatinmicroservice.repositories.ParadaRepository;
import org.example.monopatinmicroservice.repositories.PausaRepository;
import org.example.monopatinmicroservice.repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class DataLoaderHelper {

    private final MonopatinRepository monopatinRepository;

    private final ParadaRepository paradaRepository;

    private final PausaRepository pausaRepository;

    private final ViajeRepository viajeRepository;

    public DataLoaderHelper(MonopatinRepository monopatinRepository, ParadaRepository paradaRepository, PausaRepository pausaRepository, ViajeRepository viajeRepository) {
        this.monopatinRepository = monopatinRepository;
        this.paradaRepository = paradaRepository;
        this.pausaRepository = pausaRepository;
        this.viajeRepository = viajeRepository;
    }

    @Transactional
    public void loadParadas() {
        List<String[]> paradas = CSVReaderHelper.readCSV("MonopatinMicrosevice/src/main/java/org/example/monopatinmicroservice/utils/paradas.csv");

        for (String[] parada : paradas.subList(1, paradas.size())) {
            Parada p = new Parada();
//            p.setId(Long.parseLong(parada[0]));
            // Set Monopatin
//            Monopatin m = monopatinRepository.findById(Long.parseLong(parada[1])).orElseThrow();
//            p.setMonopatin(m);
            paradaRepository.save(p);
        }
    }

    public void loadMonopatines(){
        List<String[]> monopatines = CSVReaderHelper.readCSV("MonopatinMicrosevice/src/main/java/org/example/monopatinmicroservice/utils/monopatines.csv");
        for (String[] mono : monopatines.subList(1, monopatines.size())) {
            Monopatin m = new Monopatin();
            m.setId((long) Integer.parseInt(mono[0]));
            // Set Parada
            Parada p = paradaRepository.findById(Long.parseLong(mono[4])).orElseThrow();
            m.setParada(p);
            monopatinRepository.save(m);
        }
    }

    public void loadViajes(){
        List<String[]> viajes = CSVReaderHelper.readCSV("MonopatinMicrosevice/src/main/java/org/example/monopatinmicroservice/utils/viajes.csv");
        for (String[] viaje : viajes.subList(1, viajes.size())) {
            Viaje v = new Viaje();
            v.setId((long) Integer.parseInt(viaje[0]));
            // Set Monopatin
            Monopatin m = monopatinRepository.findById(Long.parseLong(viaje[4])).orElseThrow();
            v.setMonopatin(m);
            viajeRepository.save(v);
        }
    }

    public void loadPausas(){
        List<String[]> pausas = CSVReaderHelper.readCSV("MonopatinMicrosevice/src/main/java/org/example/monopatinmicroservice/utils/pausas.csv");
        for (String[] pausa : pausas.subList(1, pausas.size())) {
            Pausa p = new Pausa();
            p.setId((long) Integer.parseInt(pausa[0]));
            // Set Viaje
            Viaje v = viajeRepository.findById(Long.parseLong(pausa[2])).orElseThrow();
            p.setViaje(v);
            pausaRepository.save(p);
        }
    }
}
