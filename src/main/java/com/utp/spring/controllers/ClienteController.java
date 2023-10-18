package com.utp.spring.controllers;

import com.utp.spring.models.entity.Cliente;
import com.utp.spring.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;

    @GetMapping("/lista_clientes")
    public String listarClientes(Model modelo){
        List<Cliente> listaClientes = clienteService.findAll();
        modelo.addAttribute("listaClientes", listaClientes);
        return "crud_clientes";
    }
}
