package com.utp.spring.controllers;

import com.utp.spring.models.entity.Auditoria;
import com.utp.spring.models.entity.Proveedor;
import com.utp.spring.services.IAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/auditoria")
public class AuditoriaController {

    @Autowired
    IAuditoriaService auditoriaService;



    @GetMapping("/lista_auditorias")
    public String listarAuditorias(Model modelo){
        List<Auditoria> listaAuditorias= auditoriaService.findAll();
        modelo.addAttribute("listaAuditorias", listaAuditorias);
        return "crud_auditorias";
    }

    @GetMapping("/crear")
    public String mostrarFormularioDeNuevaAuditoria(Model modelo){

        modelo.addAttribute("auditoria",new Auditoria());

        return "auditoria_agregar";
    }

    @PostMapping("/guardar")
    public String guardarAuditoria(Auditoria auditoria) throws IOException {

        auditoria.setEstado("Pendiente");
        auditoriaService.save(auditoria);
        return "redirect:/auditoria/lista_auditorias";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id){
        auditoriaService.delete(id);
        return "redirect:/auditoria/lista_auditorias";
    }

    @GetMapping("editar/{id}")
    public String edit(@PathVariable Long id, Model modelo){
        Auditoria auditoria = new Auditoria();
        auditoria=auditoriaService.findbyId(id);
        modelo.addAttribute("auditoria",auditoria);
        return "auditoria_editar";
    }

    @PostMapping("/update")
    public String update(Auditoria auditoria) throws IOException {

        auditoriaService.save(auditoria);
        return "redirect:/auditoria/lista_auditorias";
    }


}
