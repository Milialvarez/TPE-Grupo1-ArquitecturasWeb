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
    private int id_usuario;
    private int id_monopatin;
}