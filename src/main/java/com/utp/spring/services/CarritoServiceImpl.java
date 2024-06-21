package com.utp.spring.services;

import com.utp.spring.models.dao.ICarritoDao;
import com.utp.spring.models.dao.IProductoDAO;
import com.utp.spring.models.dao.IUsuarioDAO;
import com.utp.spring.models.dto.ProductoDTO;
import com.utp.spring.models.entity.Carrito;
import com.utp.spring.models.entity.CarritoItem;
import com.utp.spring.models.entity.Producto;
import com.utp.spring.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public Carrito save(String correo) {
        Optional <Carrito>  carritoExiste =carritoDao.findByEmail(correo);

        if (carritoExiste.isEmpty()) {
            Optional<Usuario> optionalUsuario = usuarioDAO.findByEmail(correo);
            Usuario usuario = optionalUsuario.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            LocalDateTime now = LocalDateTime.now();
            Carrito carrito = new Carrito();
            carrito.setUsuario(usuario);
            carrito.setFecha_creacion(LocalDate.now());
            return carritoDao.save(carrito);
        }
        throw new RuntimeException("El usuario ya tiene un carrito asociado");
    }

    @Override
    public Carrito update(String correo) {
        double total = 0;
        Optional<Carrito> carrito=carritoDao.findByEmail(correo);
        if (carrito.isPresent()){
            Carrito carritoObtenido = carrito.get();
            List<CarritoItem> items = carritoDao.findTotal(carritoObtenido.getId());
            for (CarritoItem item : items) {
                total += item.getTotal();
            }
            carritoObtenido.setFecha_actualiz(LocalDate.now());
            carritoObtenido.setTotal(total);
            return carritoDao.save(carritoObtenido);
        }
        throw new RuntimeException("No se pudo encontrar el carrito del usuario");
    }
}
