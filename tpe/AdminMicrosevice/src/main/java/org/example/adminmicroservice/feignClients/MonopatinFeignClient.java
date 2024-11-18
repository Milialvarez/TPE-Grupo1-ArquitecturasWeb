package org.example.adminmicroservice.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="MonopatinMicroservice", url="http://localhost:8009/monopatin")
public interface MonopatinFeignClient {

    @GetMapping("/xViajes/{xViajes}/anio/{anio}")
    ResponseEntity<?> getMonopatinesPorViajesPorAnio(@PathVariable("anio") Integer anio, @PathVariable("xViajes") Integer xViajes);

}
