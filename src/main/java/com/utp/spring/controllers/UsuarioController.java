package com.utp.spring.controllers;

import com.utp.spring.models.entity.Categoria;
import com.utp.spring.models.entity.Producto;
import com.utp.spring.models.entity.Usuario;
import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;


    BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    @GetMapping("/listausuarios")
    public String listarUsuarios(Model modelo){
        List<Usuario> listaUsuarios = usuarioService.findAll();
        modelo.addAttribute("listaUsuarios", listaUsuarios);
        return "table_usuario";
    }

    @GetMapping("/registro")
    public String crear(){
        return "registro_usuario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(Usuario usuario){
        usuario.setRol("USER");
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioService.save(usuario);

    return "redirect:/";
    }

    @GetMapping("/login")
    String login(){
        return "index";
    }

    @GetMapping("/acceder")
    public String acceder (Usuario usuario, HttpSession session){
        Optional<Usuario> user=usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString()));
        if (user.isPresent()){
            session.setAttribute("idusuario",user.get().getIdusuario());
            session.setAttribute("rolusuario",user.get().getRol());
            return "redirect:/inicio";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session){
        session.removeAttribute("idusuario");
        session.removeAttribute("rolusuario");
        return "redirect:/";
    }


}
