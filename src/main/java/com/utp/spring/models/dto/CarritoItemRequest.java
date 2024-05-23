package com.utp.spring.models.dto;

import lombok.Data;

@Data
public class CarritoItemRequest {
    private String correo;
    private ProductoDTO producto;
}
