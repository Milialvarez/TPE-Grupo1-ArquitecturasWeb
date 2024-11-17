import jdk.jfr.Description;
import org.example.monopatinmicroservice.controllers.MonopatinController;
import org.example.monopatinmicroservice.controllers.ParadaController;
import org.example.monopatinmicroservice.dtos.MonopatinConIdParadaDTO;
import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.entities.Parada;
import org.junit.jupiter.api.*;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MonopatinTest {
    private static MonopatinController monopatinController;
    private static Parada p;

    @BeforeAll
    public static void setUp() {
        ParadaController paradaController = new ParadaController();
        monopatinController = new MonopatinController();
        p = new Parada();
        paradaController.addParada(p);
    }

    @Test
    @Description("Verifica que el código de respuesta ante un id inexistente es 404")
    public void testMonopatinNotFound(){
        Long inexistentId = (long) -1;
        ResponseEntity<?> response = monopatinController.getMonopatin(inexistentId);
        int statusCode = response.getStatusCodeValue();
        assertEquals(404, statusCode);
    }


}