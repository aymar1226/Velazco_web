package com.utp.spring.controllers;

import com.utp.spring.models.entity.Categoria;
import com.utp.spring.models.entity.Usuario;
import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private IUsuarioService usuarioService;
    @GetMapping("")
    public String verIndex(){

        return "index";
    }


    @GetMapping("/inicio")
    public String verInicio(Model modelo,HttpSession session) {

        if(session.getAttribute("idusuario")!=null){
            modelo.addAttribute(usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString())).get());
        }

        modelo.addAttribute("rolsesion",session.getAttribute("rolusuario"));


        return "inicio";
    }



    @GetMapping("/nosotros")
    public String verNosotros(Model modelo,HttpSession session){

        if(session.getAttribute("idusuario")!=null){
            modelo.addAttribute(usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString())).get());
        }

        modelo.addAttribute("rolsesion",session.getAttribute("rolusuario"));
        return "nosotros";
    }


    @GetMapping("/admin")
    public String verAdmin(){
        return "administrador/admin";
    }

}
