package com.utp.spring.controllers;

import com.utp.spring.models.entity.Tarjeta;
import com.utp.spring.models.entity.Usuario;
import com.utp.spring.services.ITarjetaService;
import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class TarjetaController {
    @Autowired
    private ITarjetaService tarjetaService;
    @Autowired
    private IUsuarioService usuarioService;

    BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    @GetMapping("/pago")
    public String verPago(){

        return "Pago";
    }

    @PostMapping("/pagartarjeta")
    public String pagarConTarjeta(Tarjeta tarjeta, HttpSession session, RedirectAttributes redirectAttributes){

        Usuario usuario =usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString())).get();

            tarjeta.setUsuario(usuario);
            tarjeta.setCvv(passwordEncoder.encode(tarjeta.getCvv()));

            tarjetaService.save(tarjeta);
            redirectAttributes.addFlashAttribute("mensaje", "");

        return "redirect:/inicio";
    }

}
