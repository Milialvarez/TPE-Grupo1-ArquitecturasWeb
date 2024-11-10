import org.example.usermicroservice.entities.Bill;
import org.example.usermicroservice.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    BillRepository billRepository;

    MonopatinFeignClient monopatinFeignClient;

    public List<Bill> getAll() {
        return billRepository.findAll();
    }


    public Bill save(Bill b) {
        this.billRepository.save(b);
        return b;
    }

    public boolean delete(Bill b) {
        billRepository.delete(b);
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    public Bill setNewPrice(Long id, double price) {
        Bill b = this.getBillById(id);
        if (b != null) this.billRepository.setNewPrice(id, price);
        return b;
    }

    public double getTotalBilled(LocalDate origin, LocalDate end){
        List<?> viajes = this.monopatinFeignClient.getViajesBetween(origin, end);
    }
}