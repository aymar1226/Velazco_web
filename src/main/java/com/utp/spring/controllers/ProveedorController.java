package com.utp.spring.controllers;

import com.utp.spring.models.dao.IProveedorDAO;
import com.utp.spring.models.entity.*;
import com.utp.spring.services.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;

    @GetMapping("/lista")
    public List<Proveedor> listarProveedores() {
        return proveedorService.findAll();
    }



/*--------------------------------------Controllers---------------------------------------------*/
    @GetMapping("/lista_proveedores")
    public String listarProveedores(Model modelo){
        List<Proveedor> listaProveedores= proveedorService.findAll();
        modelo.addAttribute("listaProveedores", listaProveedores);
        return "crud_proveedores";
    }

    @GetMapping("/crear")
    public String mostrarFormularioDeNuevoProveedor(Model modelo){

        modelo.addAttribute("proveedor",new Proveedor());

        return "proveedor_agregar";
    }

    @PostMapping("/guardar")
    public String guardarProveedor(Proveedor proveedor) throws IOException {

        proveedorService.save(proveedor);
        return "redirect:/proveedor/lista_proveedores";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id){
        proveedorService.delete(id);
        return "redirect:/proveedor/lista_proveedores";
    }

    @GetMapping("editar/{id}")
    public String edit(@PathVariable Long id, Model modelo){
        Proveedor proveedor = new Proveedor();
        Optional<Proveedor> optionalProveedor=proveedorService.findbyId(id);
        proveedor=optionalProveedor.get();
        modelo.addAttribute("proveedor",proveedor);
        return "proveedor_editar";
    }

    @PostMapping("/update")
    public String update(Proveedor proveedor) throws IOException {

        proveedorService.save(proveedor);
        return "redirect:/proveedor/lista_proveedores";
    }


}
