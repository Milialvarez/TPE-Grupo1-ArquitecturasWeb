package org.example.adminmicroservice.models;

import lombok.Data;

import java.util.Date;

@Data
public class Bill {
    long id;
    private Date fecha;
    float price;
    float additionalPrice;

    public Bill(long id, Date fecha, float pFijo, float pExtra) {
        this.id = id;
        this.fecha = fecha;
        this.price = pFijo;
        this.additionalPrice = pExtra;
    }
}
