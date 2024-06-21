package com.utp.spring.models.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    public enum Currency{
        PEN, USD
    }
    private String descripcion;
    private int amount;
    private Currency currency;
}
