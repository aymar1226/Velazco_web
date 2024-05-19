    package com.utp.spring.controllers;

import com.utp.spring.models.entity.DetalleOrden;
import com.utp.spring.models.entity.Orden;
import com.utp.spring.models.entity.Producto;
import com.utp.spring.models.entity.Usuario;
import com.utp.spring.services.IDetalleOrdenService;
import com.utp.spring.services.IOrdenService;
import com.utp.spring.services.IProductoService;
import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class OrdenController {
    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IDetalleOrdenService detalleOrdenService;

    //Almacenar detalles de orden
    List<DetalleOrden> detalles= new ArrayList<DetalleOrden>();

    //datos de la orden
    Orden orden = new Orden();

    /*
    @PostMapping("/carrito")
    public String AñadirCarrito(@RequestParam Long id, @RequestParam Integer cantidad, Model modelo){
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio_venta(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);

        //validar que el producto no se añada 2 veces
        Long idProducto=producto.getIdproducto();
        boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getIdproducto()==idProducto);

        if (!ingresado){
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        modelo.addAttribute("carrito",detalles);
        modelo.addAttribute("orden",orden);

        return "carrito";
    }*/

    //quitar el producto del carrito
   /* @GetMapping("/eliminar/carrito/{id}")
    public String eliminarProductoCarrito(@PathVariable Long id,Model modelo){

        //lista nueva de productos
        List<DetalleOrden> ordenesNueva=new ArrayList<DetalleOrden>();

        for (DetalleOrden detalleOrden : detalles){
            if (detalleOrden.getProducto().getIdproducto() != id) {
                ordenesNueva.add(detalleOrden);
            }
        }

        //poner nueva lista con productos restantes
        detalles=ordenesNueva;

        double sumaTotal=0;
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        modelo.addAttribute("carrito",detalles);
        modelo.addAttribute("orden",orden);

        return "carrito";
    }*/

    @GetMapping("/getCarrito")
    public String getCarrito(Model modelo,HttpSession session){

        modelo.addAttribute("carrito",detalles);
        modelo.addAttribute("orden",orden);

        //sesion
        modelo.addAttribute("sesion",session.getAttribute("idusuario"));
        return "carrito";
    }

    //ORDEN

    @GetMapping("/orden")
    public String orden(Model modelo, HttpSession session){

        Usuario usuario =usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString())).get();


        modelo.addAttribute("carrito",detalles);
        modelo.addAttribute("orden",orden);
        modelo.addAttribute("usuario",usuario);

        return "resumenorden";
    }

   /* @GetMapping("/guardarOrden")
    public String guardarOrden(HttpSession session){
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        //usuario
        Usuario usuario =usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString())).get();

        orden.setUsuario(usuario);
        ordenService.save(orden);

        //guardar detalles
        for(DetalleOrden dt:detalles){
            dt.setOrden(orden);
            detalleOrdenService.save(dt);
        }

        //limpiar lista y orden
        orden = new Orden();
        detalles.clear();

        return "redirect:/pago";
    }*/

}
