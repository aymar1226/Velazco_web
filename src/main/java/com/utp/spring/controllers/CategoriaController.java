package com.utp.spring.controllers;

import com.utp.spring.models.dao.ICategoriaDAO;
import com.utp.spring.models.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CategoriaController {

    @Autowired
    private ICategoriaDAO categoriaDAO;

    @GetMapping("/categorias")
    public String listarCategoria(Model modelo, HttpSession session){
        session.getAttribute("idusuario");
        modelo.addAttribute("sesion",session.getAttribute("idusuario"));

        session.getAttribute("rolusuario");
        modelo.addAttribute("rolsesion",session.getAttribute("rolusuario"));

        List<Categoria> listaCategorias = categoriaDAO.findAll();
        modelo.addAttribute("listaCategorias", listaCategorias);
        return "productos";
    }
}
