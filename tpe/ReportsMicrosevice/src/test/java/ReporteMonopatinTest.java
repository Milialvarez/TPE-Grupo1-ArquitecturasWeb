import org.example.reportsmicroservice.controllers.ReportsController;
import org.example.reportsmicroservice.entities.ReporteMonopatinesUso;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReporteMonopatinTest {
    private static ReportsController controller;

    @Test
    public void testCorrectResponse() {
        controller = new ReportsController();
        ResponseEntity<?> response = controller.getReporteUsoMonopatinKm(52000);
        ArrayList<ReporteMonopatinesUso> castedResponse = (ArrayList<ReporteMonopatinesUso>) response.getBody();
        assert castedResponse != null;
        assertEquals(castedResponse.getClass(), ArrayList.class);
    }
}
