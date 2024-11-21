package org.example.monopatinmicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeConIdMonopatinDTO {
    private LocalDate fecha;
    private Integer duracion;
    private Float kilometros;
    private Long id_usuario;
    private Long id_monopatin;
}