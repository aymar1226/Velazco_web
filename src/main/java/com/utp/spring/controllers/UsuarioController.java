package com.utp.spring.controllers;

import com.utp.spring.models.entity.*;
import com.utp.spring.services.IClienteService;
import com.utp.spring.services.IRolService;
import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IRolService rolService;
    @Autowired
    private IClienteService clienteService;
    BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    @GetMapping("/listausuarios")
    public String listarUsuarios(Model modelo){
        List<Usuario> listaUsuarios = usuarioService.findAll();
        modelo.addAttribute("listaUsuarios", listaUsuarios);
        return "crud_usuarios";
    }

    @GetMapping("/registro")
    public String crear(){
        return "registro_usuario";
    }



    @GetMapping("/login")
    String login(){
        return "index";
    }



    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session){
        session.removeAttribute("idusuario");
        session.removeAttribute("rolusuario");
        return "redirect:/";
    }


    @PostMapping("/cambiar_contraseña")
    public String cambiarContraseña(@RequestParam Long id,String rawPassword,Model model,String newPassword,
                                    RedirectAttributes redirectAttributes){
        Usuario usuario = new Usuario();
        Optional<Usuario> optionalUsuario = usuarioService.findbyId(id);
        usuario = optionalUsuario.get();

        boolean passwordMatch = passwordEncoder.matches(rawPassword,usuario.getPassword());
        if(passwordMatch){
            usuario.setPassword(passwordEncoder.encode(newPassword));
            model.addAttribute("activeTab", "password-tab");
            redirectAttributes.addFlashAttribute("message", "Contraseña cambiada correctamente");
            usuarioService.save(usuario);
        }else{
            redirectAttributes.addFlashAttribute("error", "Contraseña actual incorrecta");
        }

        return "redirect:/usuario/editar/" + id;
    }

}
