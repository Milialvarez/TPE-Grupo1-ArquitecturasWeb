package org.example.adminmicroservice.controllers;

import org.example.adminmicroservice.dtos.BillDTO;
import org.example.adminmicroservice.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body("Todo ok andando en admin");
    }

    @GetMapping("/role/{role}") //role must be either: [usuario, admin, mantenimiento]
    public ResponseEntity<?> getUsersByRole(@PathVariable("role") String r) {
        return ResponseEntity.status(200).body(this.adminService.getUsersByRole(r).getBody());
    }

    @GetMapping("/xViajes/{xViajes}/anio/{anio}") //ANDA
    public ResponseEntity<?> getMonopatinesPorViajesPorAnio(@PathVariable("anio") Integer anio, @PathVariable("xViajes") Integer xViajes){
        ResponseEntity<?> reportViajes = adminService.getMonopatinesPorViajesPorAnio(anio, xViajes);
        return ResponseEntity.ok(reportViajes.getBody());
    }

    @PutMapping("/null/{id_acc}") //ANDA PERO CON DELAY, la primera vez que se manda parece que no cambió nada, pero si lo volvés a llamar se ven los cambios
    public ResponseEntity<?> anullateAccount(@PathVariable("id_acc") Integer id_acc) {
       return ResponseEntity.ok(adminService.anullateAccount(id_acc));
    }

//no anda
    @GetMapping("/totalBilled/origen/{fechaOrigen}/fin/{fechaFin}")
    public ResponseEntity<?> getTotalBilled(@PathVariable("fechaOrigen") String o, @PathVariable("fechaFin") String e) {
        try {
            LocalDate origin = LocalDate.parse(o);
            LocalDate end = LocalDate.parse(e);
            if (origin.isAfter(end)) {
                return ResponseEntity.badRequest().body("wrong dates");
            }
            ResponseEntity<?> reporteTotalFacturadoEntreFechas = adminService.getTotalBilled(origin, end);
            return ResponseEntity.status(HttpStatus.OK).body(reporteTotalFacturadoEntreFechas);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body("Disculpe, estamos trabajando para solucionarlo ;)");
        }
    }

    @GetMapping("/activosVsMantenimiento")
    public ResponseEntity<?> getReporteMonopatinesSegunEstado(){
        ResponseEntity<?> result = this.adminService.getReporteMonopatinesSegunEstado();
        String response = Objects.requireNonNull(result.getBody()).toString();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/newBilling")
    public ResponseEntity<?> setNewBill(@RequestBody BillDTO bill){ //no se pudo determinar por qué cuando la fecha es menor a la valida se va al catch
        try {
            ResponseEntity<?> response = this.adminService.setNewBill(bill);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}