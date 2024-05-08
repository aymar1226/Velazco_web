package com.utp.spring.controllers;

import com.utp.spring.models.dao.IProductoDAO;
import com.utp.spring.models.entity.Categoria;
import com.utp.spring.models.entity.Producto;
import com.utp.spring.models.entity.Usuario;
import com.utp.spring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductoController {


    @Autowired
    private IProductoService productoService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private ICategoriaService categoriaService;
    @Autowired
    private UploadFileService upload;

    @GetMapping("/productos")
    public String VerProductos(){
        return "productos";
    }




    @GetMapping("/listaproductos")
    public List<Producto> listarProductos() {
        return productoService.findAll();
    }


    @GetMapping("/bizcochos")
    public String filtrarBizcocho(Model modelo, HttpSession session){
        //validando si es nulo
        if(session.getAttribute("idusuario")!=null){
            modelo.addAttribute(usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString())).get());
        }
        //eleccion header
        modelo.addAttribute("rolsesion",session.getAttribute("rolusuario"));
        //FILTRO
        Long categoriaID = 1l;
        List<Producto> listaProductos = productoService.listAll(categoriaID);
        modelo.addAttribute("listaProductos", listaProductos);
        return "categoria/bizcochos";
    }
    @GetMapping("/galletas")
    public String filtrarGalleta(Model modelo,HttpSession session){
        //validando si es nulo
        if(session.getAttribute("idusuario")!=null){
            modelo.addAttribute(usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString())).get());
        }

        //eleccion header
        modelo.addAttribute("rolsesion",session.getAttribute("rolusuario"));

        //FILTRO
        Long categoriaID = 2l;
        List<Producto> listaProductos = productoService.listAll(categoriaID);
        modelo.addAttribute("listaProductos", listaProductos);
        return "categoria/galletas";
    }

    @GetMapping("/pasteles")
    public String filtrarPastel(Model modelo,HttpSession session){
        //validando si es nulo
        if(session.getAttribute("idusuario")!=null){
            modelo.addAttribute(usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString())).get());
        }
        //eleccion header
        modelo.addAttribute("rolsesion",session.getAttribute("rolusuario"));
        //FILTRO
        Long categoriaID = 3l;
        List<Producto> listaProductos = productoService.listAll(categoriaID);
        modelo.addAttribute("listaProductos", listaProductos);
        return "categoria/pasteles";
    }
    @GetMapping("/dulces")
    public String filtrarDulce(Model modelo,HttpSession session){
        //validando si es nulo
        if(session.getAttribute("idusuario")!=null){
            modelo.addAttribute(usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString())).get());
        }

        //eleccion header
        modelo.addAttribute("rolsesion",session.getAttribute("rolusuario"));

        //FILTRO
        Long categoriaID = 4l;
        List<Producto> listaProductos = productoService.listAll(categoriaID);
        modelo.addAttribute("listaProductos", listaProductos);
        return "categoria/dulces";
    }

    @GetMapping("/productohome/{id}")
    public String productoHome(@PathVariable Long id,Model modelo,HttpSession session){

        Producto producto = new Producto();
        Optional<Producto> productoOptional= productoService.get(id);
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
        if(producto.getIdproducto()==null){
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
        Optional<Producto> optionalProducto=productoService.get(id);
        producto=optionalProducto.get();
        modelo.addAttribute("producto",producto);

        List<Categoria>listaCategorias = categoriaService.findAll();
        modelo.addAttribute("listaCategorias",listaCategorias);
        return "producto_formulario";
    }

    @PostMapping("/productos/update")
    public String update(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {
        Producto p=new Producto();
        p=productoService.get(producto.getIdproducto()).get();
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
        p=productoService.get(id).get();

        //eliminar cuando no sea imagen por defecto
        if(!p.getImagen().equals("default.jpg")){
            upload.deleteImage(p.getImagen());
        }

        productoService.delete(id);
        return "redirect:/listaproductos";
    }

}
