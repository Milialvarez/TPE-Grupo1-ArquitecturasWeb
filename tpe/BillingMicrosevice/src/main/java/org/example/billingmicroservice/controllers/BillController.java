import org.example.usermicroservice.entities.Role;
import org.example.usermicroservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bills")
public class BillController{
    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<?> getAllBills(){
        ArrayList<Bill> billService.getAllBills();

    }

    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen")LocalDate origin,@PathVariable("fechaFin") LocalDate end){
        try {
            List<Object[]> reporteTotalFacturadoEntreFechas = billService.getTotalBilled(origin, end);
            return ResponseEntity.ok(reporteTotalFacturadoEntreFechas);
        } catch () {

        }

    }



}