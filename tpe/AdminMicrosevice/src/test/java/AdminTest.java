import jdk.jfr.Description;
import org.example.adminmicroservice.controllers.AdminController;
import org.example.adminmicroservice.dtos.BillDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdminTest {
    private static AdminController adminController;

    @BeforeAll
    public static void setUp(){
        adminController = new AdminController();
    }

    @Test
    @Description("Verifica que el código de respuesta sea 201")
    public void testBillException(){
        ResponseEntity<?> response = adminController.setNewBill(new BillDTO());
        String statusCode = response.getStatusCode().toString();
        assertEquals("201", statusCode);
    }
}
