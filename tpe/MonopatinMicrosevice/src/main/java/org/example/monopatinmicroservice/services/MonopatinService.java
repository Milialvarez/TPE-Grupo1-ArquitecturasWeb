package org.example.monopatinmicroservice.services;

import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.feignClients.ManteinanceFeignClient;
import org.example.monopatinmicroservice.models.Mantenimiento;
import org.example.monopatinmicroservice.repositories.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepository monopatinRepository;
    private ManteinanceFeignClient mfc;

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

    public ArrayList<Monopatin> getClosestMonopatins(int posx, int posy) throws Exception {
        try{
            List<Monopatin> monopatines= this.monopatinRepository.getMonopatinesEnRadio1km(posx,posy);
            List<Monopatin> respuesta = new ArrayList<>();
            for (Monopatin monopatin : monopatines) {
                respuesta.add(monopatin);
            }
            return (ArrayList<Monopatin>) respuesta;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public double calcularDistancia(Monopatin m, int x, int y) {
        return Math.sqrt(Math.pow(x - m.getPosX(), 2) + Math.pow(y - m.getPosY(), 2));
    }


    public ArrayList<Monopatin> getMonopatinesEnMantenimiento() {
        ResponseEntity<?> mantenimientos = this.mfc.getAllManteinanceUnvailable();
        ArrayList<Mantenimiento> response = (ArrayList<Mantenimiento>) mantenimientos.getBody();
        ArrayList<Monopatin> result = new ArrayList<>();
        for(Mantenimiento m: response){
            result.add(this.monopatinRepository.findById(m.getId_monopatin()).orElse(null));
        }
        return result;
    }

    public ArrayList<Monopatin> getMonopatinesActivos() {
        ResponseEntity<?> activos = this.mfc.getAllManteinanceActive();
        ArrayList<Mantenimiento> response = (ArrayList<Mantenimiento>) activos.getBody();
        ArrayList<Monopatin> result = new ArrayList<>();
        for(Mantenimiento m: response){
            result.add(this.monopatinRepository.findById(m.getId_monopatin()).orElse(null));
        }
        return result;
    }

    public ResponseEntity<?> enviarMonopatinAMantenimiento(Monopatin monopatin) {
        int limiteKm = 40000; //limite de km a partir del cual llevar a mantener
        Map<String, String> response = new HashMap<>();
        if(monopatin == null || this.monopatinRepository.findById(monopatin.getId()) == null){
            response.put("error", "monopatin no encontrado");
            return ResponseEntity.badRequest().body(response);
        }
        if(monopatin.getKmRecorridos()<limiteKm){
            response.put("Inhabilitado", "el monopatín no necesita mantenimiento");
            return ResponseEntity.ok().body(response);
        } else{
            ResponseEntity<?> result = this.mfc.saveManteinance(monopatin.getId());
            Mantenimiento m = (Mantenimiento) result.getBody();
            return ResponseEntity.ok().body(m);
        }
    }

    public ResponseEntity<?> cambiarEstadoMonopatin(Monopatin monopatin, String estado) {
        Map<String, String> response = new HashMap<>();
        if(estado == null || estado != "activo" && estado != "no disponible"){
            response.put("error", "estado invalido o nulo");
            return ResponseEntity.badRequest().body(response);
        } else if(this.mfc.getManteinanceByMonopatinId(monopatin.getId()) == null){
            response.put("error", "monopatin no encontrado");
            return ResponseEntity.badRequest().body(response);
        } else{
            this.mfc.updateStatus(monopatin, estado);
            response.put("success", "estado del monopatin cambiado");
            return ResponseEntity.ok().body(response);
        }
    }

}
