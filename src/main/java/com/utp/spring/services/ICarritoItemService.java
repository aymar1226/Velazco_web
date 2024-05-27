package com.utp.spring.services;


import com.utp.spring.models.dto.ProductoDTO;
import com.utp.spring.models.entity.Carrito;
import com.utp.spring.models.entity.CarritoItem;

import java.util.List;

public interface ICarritoItemService {

    public CarritoItem save (String correo,ProductoDTO productoDTO);
    public List<CarritoItem> findAll(String correo);
    public void deleteItem (Long id);


}
