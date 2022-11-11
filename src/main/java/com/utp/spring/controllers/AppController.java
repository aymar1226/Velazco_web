package com.utp.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AppController {

    @GetMapping("")
    public String verIndex(){

        return "index";
    }

    @GetMapping("/inicio")
    public String verInicio(Model modelo,HttpSession session){
        session.getAttribute("idusuario");
        modelo.addAttribute("sesion",session.getAttribute("idusuario"));

        session.getAttribute("rolusuario");
        modelo.addAttribute("rolsesion",session.getAttribute("rolusuario"));

        return "inicio";
    }
    @GetMapping("/nosotros")
    public String verNosotros(Model modelo,HttpSession session){
        session.getAttribute("idusuario");
        modelo.addAttribute("sesion",session.getAttribute("idusuario"));

        session.getAttribute("rolusuario");
        modelo.addAttribute("rolsesion",session.getAttribute("rolusuario"));
        return "nosotros";
    }


    @GetMapping("/admin")
    public String verAdmin(){
        return "administrador/admin";
    }
}
