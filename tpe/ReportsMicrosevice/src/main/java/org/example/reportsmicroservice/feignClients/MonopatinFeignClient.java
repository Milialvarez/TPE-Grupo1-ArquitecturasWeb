package org.example.reportsmicroservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="MonopatinMicroservice", url="http://localhost:8003/monopatin")
public interface MonopatinFeignClient {

   @GetMapping("/activos")
   ResponseEntity<?> getMonopatinesActivos();
   @GetMapping("/mantenimiento")
   ResponseEntity<?> getMonopatinesEnMantenimiento();

   @GetMapping( "/{km}")
   ResponseEntity<?> getMonopatinesPorKm(@PathVariable(value = "km") float maxKmRecorridos);

   @GetMapping( "/{t}/{pausa}")
   ResponseEntity<?> getMonopatinesPorTiempo(@PathVariable(value = "t") float t, @PathVariable(value = "pausa", required = false) boolean pausa);
}
