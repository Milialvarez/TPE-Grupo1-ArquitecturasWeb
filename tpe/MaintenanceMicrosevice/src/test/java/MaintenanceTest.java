import jdk.jfr.Description;
import org.example.maintenancemicroservice.controllers.MantenimientoController;
import org.example.maintenancemicroservice.entities.Mantenimiento;
import org.example.maintenancemicroservice.models.Monopatin;
import org.example.maintenancemicroservice.repositories.MantenimientoRepository;
import org.example.maintenancemicroservice.services.MantenimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MaintenanceTest {

    @InjectMocks
    private MantenimientoService mantenimientoService;
    @Mock
    private MantenimientoRepository mantenimientoRepository;
    ArrayList<Mantenimiento> mantenimientos;
    Monopatin monopatin;
    Mantenimiento mantenimiento;

    @BeforeEach
    void setUp() {
        monopatin = new Monopatin();
        mantenimiento = new Mantenimiento();
        monopatin.setId(1L);
        mantenimiento.setId_monopatin(monopatin.getId());
    }

    @Test
    public void testIdMonopatin(){
        when(mantenimientoService.findByMonopatinId(monopatin.getId())).thenReturn(mantenimiento);
        Mantenimiento m = mantenimientoService.findByMonopatinId(monopatin.getId());
        assertEquals(m.getId_monopatin(), monopatin.getId(), "el mantenimiento no se creó corectamente");
    }



}
