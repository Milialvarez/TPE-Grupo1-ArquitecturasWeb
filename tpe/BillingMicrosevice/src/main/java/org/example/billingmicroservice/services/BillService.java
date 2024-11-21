package org.example.billingmicroservice.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.billingmicroservice.dtos.MonopatinDTO;
import org.example.billingmicroservice.dtos.ViajeDTO;
import org.example.billingmicroservice.entities.Bill;
import org.example.billingmicroservice.feignClient.ViajeFeignClient;
import org.example.billingmicroservice.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BillService {
    @Autowired
    BillRepository billRepository;

    @Autowired
    ViajeFeignClient viajeFeignClient;

    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    public Bill save(Bill b) {
        this.billRepository.save(b);
        return b;
    }

    public boolean delete(Bill b) {
        billRepository.delete(b);
        return true;
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    public Bill setNewPrice(Long id, double price) {
        Bill b = this.getBillById(id);
        if (b != null) this.billRepository.setNewPrice(id, price);
        return b;
    }
//hace falta cambiar el calculo del costo. El precio adicinal deberia ser por cada precio fijo.
    private double getCostoViaje(ViajeDTO viaje, Bill tarifa){
        double sumatoria = 0;
        Integer duracionViaje = viaje.getDuracion();
        MonopatinDTO m = viaje.getMonopatin();
        Float tiempoPausas = m.getTiempoUsoConPausas();
        if (tiempoPausas > 15){
            sumatoria += tarifa.getAdditionalPrice();
        }
        sumatoria += duracionViaje * tarifa.getPrice();
        return sumatoria;
    }

    public Bill getCostos(){ //

        List<Bill> tarifas = this.billRepository.findAll();
        return tarifas.getFirst();
    }

    //El credito comienza a consumirse cuando se activa el viaje (es decir en t == 0)
    // Si el viaje tuvo un tiempo de pausa asociado mayor a 15 minutos, se debe calcular
    // un costo adicional que se suma al fijo por el resto del viaje

    public double getTotalBilled(LocalDate origin, LocalDate end) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String originFormatted = origin.format(formatter);
            String endFormatted = end.format(formatter);

            Bill tarifa = this.getCostos();

            // Obtener la respuesta como lista genérica
            List<?> body = this.viajeFeignClient.getViajesBetween(originFormatted, endFormatted).getBody();

            // Crear un ObjectMapper y registrar el módulo de Java Time
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            // Convertir cada elemento de la lista a ViajeDTO
            List<ViajeDTO> viajes = body.stream()
                    .map(obj -> mapper.convertValue(obj, ViajeDTO.class))
                    .toList();

            // Realizar la sumatoria
            double sumatoria = 0;
            for (ViajeDTO v : viajes) {
                sumatoria += this.getCostoViaje(v, tarifa);
            }
            return sumatoria;
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0;
        }
    }


    public Bill setNewBill(LocalDate f, float pFijo, float pEx) {
        LocalDate lastBillDate = this.billRepository.getLastOne();
        if (lastBillDate == null || f.isBefore(lastBillDate)){
            return null;
        }
        Bill b = new Bill();
        b.setFecha(f);
        b.setPrice(pFijo);
        b.setAdditionalPrice(pEx);
        return this.billRepository.save(b);
    }
}