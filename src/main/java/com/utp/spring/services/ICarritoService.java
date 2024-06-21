package com.utp.spring.services;


import com.utp.spring.models.dto.ProductoDTO;
import com.utp.spring.models.entity.Carrito;

import java.util.List;

public interface ICarritoService {

    public Carrito save (String correo);
    public Carrito update (String correo);
}
