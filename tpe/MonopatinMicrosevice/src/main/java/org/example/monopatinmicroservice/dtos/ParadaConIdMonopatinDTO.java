package org.example.monopatinmicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParadaConIdMonopatinDTO {
    private Boolean isHabilitada;
    private Long id_monopatin;
}
