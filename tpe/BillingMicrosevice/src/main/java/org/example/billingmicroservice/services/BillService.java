package org.example.billingmicroservice.services;


import org.example.billingmicroservice.entities.Bill;
import org.example.billingmicroservice.feignClient.ViajeFeignClient;
import org.example.billingmicroservice.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
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
    private double getCostoViaje(Object[] viaje, Bill tarifa){
        double sumatoria = 0;
        Integer duracionViaje = (Integer) viaje[2];
        Object[] pausa = (Object[]) viaje[3];
        if (pausa != null && (Integer) pausa[1] > 15){
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
    public double getTotalBilled(LocalDate origin, LocalDate end){
        try {
            Bill tarifa = this.getCostos();
            List<Object[]> viajes = (List<Object[]>) this.viajeFeignClient.getViajesBetween(origin, end);
            double sumatoria = 0;
            for (Object[] v : viajes){
                sumatoria += this.getCostoViaje(v, tarifa);
            }
            return sumatoria;
        } catch (Exception e) {
            return -1.0;
        }

    }

    public Bill setNewBill(Date f, float pFijo, float pEx) {
        Date lastBillDate = this.billRepository.getLastOne();
        if (f.before(lastBillDate)) return null;
        Bill b = new Bill();
        b.setFecha(f);
        b.setPrice(pFijo);
        b.setAdditionalPrice(pEx);
        return this.billRepository.save(b);
    }
}