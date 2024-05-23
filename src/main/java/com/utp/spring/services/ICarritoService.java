package com.utp.spring.services;


import com.utp.spring.models.dto.ProductoDTO;
import com.utp.spring.models.entity.Carrito;
import com.utp.spring.models.entity.Orden;
import com.utp.spring.models.entity.Usuario;

import java.util.List;

public interface ICarritoService {

    public Carrito save (Usuario usuario);
    public Carrito update (String correo, List<ProductoDTO> productos);
}
