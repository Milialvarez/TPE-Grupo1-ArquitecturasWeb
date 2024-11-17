import jdk.jfr.Description;
import org.example.maintenancemicroservice.controllers.MantenimientoController;
import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.example.maintenancemicroservice.models.Monopatin;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaintenanceTest {
    private static MantenimientoController mantenimientoController;

    @Test
    @Description("Test para validar que a un mantenimiento se le asigna correctamente el id de su monopatin")
    public void testCorrectPost() {
        mantenimientoController = new MantenimientoController();
        Long idMonopatin = 1L;
        ResponseEntity<?> response = mantenimientoController.saveManteinance(idMonopatin);
        LinkedHashMap<?, ?> l = (LinkedHashMap<?, ?>) response.getBody();
        assert l != null;
        Long idMonopatinMantenimiento = (Long) l.get("id_monopatin");
        assertTrue(idMonopatinMantenimiento == idMonopatin, "el id monopatin del mantenimiento debería ser el mismo que se le asignó");
    }
}
