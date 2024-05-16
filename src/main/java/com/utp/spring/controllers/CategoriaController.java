package com.utp.spring.controllers;

import com.utp.spring.models.dao.ICategoriaDAO;
import com.utp.spring.models.entity.Categoria;
import com.utp.spring.services.ICategoriaService;
import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/lista")
    public List<Categoria> listarCategoria(){

        List<Categoria> listaCategorias = categoriaService.findAll();

        return listaCategorias;
    }
}
