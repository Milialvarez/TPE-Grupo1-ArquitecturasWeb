
import org.example.reportsmicroservice.dtos.MonopatinKmDTO;
import org.example.reportsmicroservice.entities.ReporteMonopatinesSinPausa;
import org.example.reportsmicroservice.entities.ReporteMonopatinesUso;
import org.example.reportsmicroservice.feignClients.MonopatinFeignClient;
import org.example.reportsmicroservice.services.ReportsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ReporteMonopatinTest {
    @InjectMocks
    private ReportsService repService;
    @Mock
    private MonopatinFeignClient monopatinFeignClient;

    @Test
    public void testCorrectResponse() {
        ArrayList<Object> result = repService.getReporteUsoMonopatinKm(true);
        assertEquals(result.getFirst().getClass(), ReporteMonopatinesUso.class, "la respuesta debería ser una entidad de Reportes con pausas");
    }

    @Test
    public void testIncorrectResponse() {
        ArrayList<Object> result = repService.getReporteUsoMonopatinKm(false);
        assertEquals(result.getFirst().getClass(), ReporteMonopatinesSinPausa.class, "La respuesta debería ser una entidad de Reportes sin pausas");
    }
}
