package org.example.monopatinmicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeConIdMonopatinDTO {
    private Date fecha;
    private Integer duracion;
    private Long id_usuario;
    private Long id_monopatin;
}