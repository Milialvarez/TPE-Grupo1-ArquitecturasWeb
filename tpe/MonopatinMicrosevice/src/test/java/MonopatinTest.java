import jdk.jfr.Description;
import org.example.monopatinmicroservice.controllers.MonopatinController;
import org.example.monopatinmicroservice.controllers.ParadaController;
import org.example.monopatinmicroservice.dtos.MonopatinConIdParadaDTO;
import org.example.monopatinmicroservice.entities.Monopatin;
import org.example.monopatinmicroservice.entities.Parada;
import org.example.monopatinmicroservice.feignClients.ManteinanceFeignClient;
import org.example.monopatinmicroservice.repositories.MonopatinRepository;
import org.example.monopatinmicroservice.services.MonopatinService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MonopatinTest {
    @InjectMocks
    private MonopatinService monopatinService;
    @Mock
    private MonopatinRepository monopatinRepository;
    @Mock
    private ManteinanceFeignClient manteinanceFeignClient;

    @Test
    @Description("Verifica que el código de respuesta ante un id inexistente es 404")
    public void testMonopatinNotFound(){
       Monopatin m = monopatinService.getById(200L);
       assertNull(m, "El monopatin debería ser null porque no existe ninguno con id 200");
    }


}