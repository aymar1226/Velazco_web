package com.utp.spring.controllers;

import com.utp.spring.models.dao.ICategoriaDAO;
import com.utp.spring.models.entity.Categoria;
import com.utp.spring.services.ICategoriaService;
import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    @Autowired
    private IUsuarioService usuarioService;
    @GetMapping("/categorias")
    public String listarCategoria(Model modelo, HttpSession session){

        if(session.getAttribute("idusuario")!=null){
            modelo.addAttribute(usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString())).get());
        }
        modelo.addAttribute("sesion",session.getAttribute("idusuario"));
        modelo.addAttribute("rolsesion",session.getAttribute("rolusuario"));

        List<Categoria> listaCategorias = categoriaService.findAll();
        modelo.addAttribute("listaCategorias", listaCategorias);
        return "productos";
    }
}
