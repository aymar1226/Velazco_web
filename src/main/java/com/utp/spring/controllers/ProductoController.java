package com.utp.spring.controllers;

import com.utp.spring.models.dao.IProductoDAO;
import com.utp.spring.models.entity.Categoria;
import com.utp.spring.models.entity.Producto;
import com.utp.spring.models.entity.Usuario;
import com.utp.spring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/productos")
public class ProductoController {


    @Autowired
    private IProductoService productoService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private ICategoriaService categoriaService;
    @Autowired
    private UploadFileService upload;

    @GetMapping("/lista")
    public List<Producto> listarProductos() {
        return productoService.findAll();
    }


    //Obtener imagen del producto
    @GetMapping("imagen/{id}")
    public ResponseEntity<Resource> getProductImage(@PathVariable Long id) throws MalformedURLException {
        String imageName = productoService.findProductImageById(id); // Obtener el nombre de la imagen del producto desde la base de datos
        Resource image = productoService.loadProductImage(imageName); // Cargar la imagen del producto desde el sistema de archivos o el almacenamiento
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.save(producto);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductbyId(@PathVariable Long id) {
        return productoService.findbyId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PutMapping("update/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id,@RequestBody Producto producto,MultipartFile multipartFile) {
        try {
            producto.setId(id);
            Producto productoModificado = productoService.save(producto);
            return new ResponseEntity<>(productoModificado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("delete/{id}")
    public ResponseEntity deleteProducto(@PathVariable Long id) {
        try {
            productoService.delete(id);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Obtener producto por categoria
    @GetMapping("/menu/{id}")
    public List<Producto> filtrarPorCategoria(@PathVariable Long id){
        List<Producto> listaProductos = productoService.listAll(id);
        return listaProductos;
    }





    //---------------------------Controllers----------------------------------------//






    @GetMapping("/productohome/{id}")
    public String productoHome(@PathVariable Long id,Model modelo,HttpSession session){

        Producto producto = new Producto();
        Optional<Producto> productoOptional= productoService.findbyId(id);
        producto = productoOptional.get();
        modelo.addAttribute("producto",producto);

        return "producto_crear";
    }

    @GetMapping("productos/nuevo")
    public String mostrarFormularioDeNuevoProducto(Model modelo){
        List<Categoria>listaCategorias = categoriaService.findAll();

        modelo.addAttribute("producto",new Producto());
        modelo.addAttribute("listaCategorias",listaCategorias);

        return "producto_agregar";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        //image
        if(producto.getId()==null){
            String nombreImagen=upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        else {
        }
        productoService.save(producto);
        return "redirect:/listaproductos";
    }

    @GetMapping("/productos/editar/{id}")
    public String edit(@PathVariable Long id,Model modelo){
        Producto producto = new Producto();
        Optional<Producto> optionalProducto=productoService.findbyId(id);
        producto=optionalProducto.get();
        modelo.addAttribute("producto",producto);

        List<Categoria>listaCategorias = categoriaService.findAll();
        modelo.addAttribute("listaCategorias",listaCategorias);
        return "producto_formulario";
    }

    @PostMapping("/productos/update")
    public String update(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {
        Producto p=new Producto();
        p=productoService.findbyId(producto.getId()).get();
        if (file.isEmpty()){

            producto.setImagen(p.getImagen());
        }else {//cuando se edita tbn la imagen
            //eliminar cuando no sea imagen por defecto
            if(!p.getImagen().equals("default.jpg")){
                upload.deleteImage(p.getImagen());
            }

            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        productoService.update(producto);
        return "redirect:/listaproductos";
    }

    @GetMapping("productos/eliminar/{id}")
    public String delete(@PathVariable Long id){

        Producto p =new Producto();
        p=productoService.findbyId(id).get();

        //eliminar cuando no sea imagen por defecto
        if(!p.getImagen().equals("default.jpg")){
            upload.deleteImage(p.getImagen());
        }

        productoService.delete(id);
        return "redirect:/listaproductos";
    }

}
