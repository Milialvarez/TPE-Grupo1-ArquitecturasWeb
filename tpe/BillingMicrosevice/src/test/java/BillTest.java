import org.example.billingmicroservice.controllers.BillController;
import org.example.billingmicroservice.entities.Bill;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillTest {
    private static BillController billController;

    @BeforeAll
    public static void setUp() {
        billController = new BillController();
    }

    @Test
    public void testAddBill() {
        Date fV = new Date();
        float pf = 1500;
        float pex = 500;

        ResponseEntity<?> response = billController.setNewBill(fV, pf, pex);
        String status = response.getStatusCode().toString();

        assertEquals("201", status, "error, se debió recibir el código 201");
    }

}
