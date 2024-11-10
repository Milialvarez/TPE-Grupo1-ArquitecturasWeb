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

    public ResponseEntity<?> getTotalBilled(LocalDate origin, LocalDate end){

    }



}