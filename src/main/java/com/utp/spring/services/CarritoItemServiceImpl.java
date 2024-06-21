package com.utp.spring.services;

import com.utp.spring.models.dao.ICarritoDao;
import com.utp.spring.models.dao.ICarritoItemDao;
import com.utp.spring.models.dao.IProductoDAO;
import com.utp.spring.models.dao.IUsuarioDAO;
import com.utp.spring.models.dto.ProductoDTO;
import com.utp.spring.models.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarritoItemServiceImpl implements ICarritoItemService {

    @Autowired
    private ICarritoItemDao carritoItemDao;

    @Autowired
    private ICarritoDao carritoDao;

    @Autowired
    private IProductoDAO productoDAO;

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public CarritoItem save(String correo, ProductoDTO productoDTO) {

        Carrito carrito = carritoDao.findByEmail(correo).get();

        Producto producto = productoDAO.findById(productoDTO.getId()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Busca si el producto ya está en el carrito
        Optional<CarritoItem> itemOptional = carritoItemDao.findByCarritoAndProducto(carrito.getId(), producto.getId());

        if (itemOptional.isPresent()) {
            // Si el producto ya está en el carrito, actualiza la cantidad y el total
            CarritoItem item = itemOptional.get();
            int nuevaCantidad = item.getCantidad() + productoDTO.getCantidad();
            item.setCantidad(nuevaCantidad);
            item.setTotal(producto.getPrecio() * nuevaCantidad);
            return carritoItemDao.save(item);
        } else {
            // Si el producto no está en el carrito, crea un nuevo CarritoItem
            CarritoItem item = new CarritoItem();
            item.setCarrito(carrito);
            item.setProducto(producto);
            item.setCantidad(productoDTO.getCantidad());
            item.setTotal(producto.getPrecio() * productoDTO.getCantidad());
            return carritoItemDao.save(item);
        }
    }


    @Override
    public List<CarritoItem> findAll(String correo) {

        Carrito carrito = carritoDao.findByEmail(correo).get();

        if(carrito!=null){
            return carritoItemDao.findByCarrito(carrito.getId());
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteItem(Long id) {

        Optional<CarritoItem> itemAEliminar = carritoItemDao.findById(id);

        if(itemAEliminar.isPresent()){
            carritoItemDao.deleteById(itemAEliminar.get().getId());
        }

    }
}
