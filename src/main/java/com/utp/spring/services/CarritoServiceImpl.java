package com.utp.spring.services;

import com.utp.spring.models.dao.ICarritoDao;
import com.utp.spring.models.dao.IProductoDAO;
import com.utp.spring.models.dto.ProductoDTO;
import com.utp.spring.models.entity.Carrito;
import com.utp.spring.models.entity.Orden;
import com.utp.spring.models.entity.Producto;
import com.utp.spring.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CarritoServiceImpl implements ICarritoService {
    @Autowired
    private ICarritoDao carritoDao;

    @Autowired
    private IProductoDAO productoDAO;

    @Override
    public Carrito save(Usuario usuario) {

        LocalDateTime now = LocalDateTime.now();
        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setFecha_creacion(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
        return carritoDao.save(carrito);
    }

    @Override
    public Carrito update(String correo, List<ProductoDTO> productos) {
        double total = 0;

        Carrito carrito=carritoDao.findByEmail(correo);

        for (ProductoDTO productoDTO : productos) {
            Producto producto = productoDAO.findById(productoDTO.getId())
                    .orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));
            total += producto.getPrecio() * productoDTO.getCantidad();
        }

        carrito.setFecha_actualiz(new Date());
        carrito.setTotal(total);

        return carritoDao.save(carrito);
    }
}
