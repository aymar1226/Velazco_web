package com.utp.spring.controllers;

import com.utp.spring.models.dto.CarritoItemRequest;
import com.utp.spring.models.dto.ProductoDTO;
import com.utp.spring.models.entity.Carrito;
import com.utp.spring.models.entity.CarritoItem;
import com.utp.spring.models.entity.Producto;
import com.utp.spring.models.entity.Usuario;
import com.utp.spring.security.JWTUtils;
import com.utp.spring.services.ICarritoItemService;
import com.utp.spring.services.ICarritoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/carrito")
public class CarritoController {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private ICarritoService carritoService;

    @Autowired
    private ICarritoItemService carritoItemService;

    @PostMapping("/crear/{correo}")
    public ResponseEntity<Carrito> crearCarrito( @PathVariable String correo) {
        try {
            Carrito nuevoCarrito= carritoService.save(correo);
            return new ResponseEntity<>(nuevoCarrito, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/item/crear")
    public ResponseEntity<CarritoItem> guardarItem(@RequestBody CarritoItemRequest request){

        try {
            CarritoItem item = carritoItemService.save(request.getCorreo(),request.getProducto());
            return new ResponseEntity<>(item,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Carrito> updateCarrito(HttpServletRequest request){
        try {
            String correo = jwtUtils.extractEmailFromRequest(request);
            System.out.println(correo);
            Carrito carritoActualizado= carritoService.update(correo);
            return new ResponseEntity<>(carritoActualizado,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/item/lista/{correo}")
    public List<CarritoItem> listarItems(@PathVariable String correo ) {
        return carritoItemService.findAll(correo);
    }

    @DeleteMapping("/item/eliminar/{id}")
    public ResponseEntity<Void> eliminarItems(@PathVariable Long id){
        try {
            carritoItemService.deleteItem(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
