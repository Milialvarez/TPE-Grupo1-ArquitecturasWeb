package org.example.adminmicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Date fechaInicioFacturacionNueva;
    private float precioFijo;
    private float precioExtra;
}
