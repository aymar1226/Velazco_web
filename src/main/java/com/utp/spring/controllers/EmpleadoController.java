package com.utp.spring.controllers;

import com.utp.spring.models.dto.PersonaUsuarioDTO;
import com.utp.spring.models.entity.*;
import com.utp.spring.services.ICargoService;
import com.utp.spring.services.IEmpleadoService;
import com.utp.spring.services.IRolService;
import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICargoService cargoService;
    @Autowired
    private IRolService rolService;



    @GetMapping("/lista")
    public List<Empleado> listarEmpleados() {
        return empleadoService.findAll();
    }

    @GetMapping("/cargo/lista")
    public List<Cargo> listarCategoria(){
        List<Cargo> listaCargos = cargoService.findAll();
        return listaCargos;
    }

    @PostMapping("/agregar")
    public ResponseEntity<Empleado> agregarEmpleado(@RequestBody Empleado empleado) {
        try {
            System.out.println(empleado);
            Empleado nuevoEmpleado = empleadoService.save(empleado);
            return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crearusuario")
    public ResponseEntity<Usuario> crearUsuarioAEmpleado(@RequestBody PersonaUsuarioDTO persona) {

        System.out.println(persona);

        try {
            Usuario nuevoUsuario = empleadoService.crearUsuarioAEmpleado(persona);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/delete")
    public ResponseEntity deleteProducto(@RequestBody Empleado empleado) {
        System.out.println(empleado);
        try {
            empleadoService.delete(empleado);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /*--------------------------------------CONTROLLERS-------------------------------------------------------------------*/
    BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    @GetMapping("/lista_empleados")
    public String listarEmpleados(Model modelo){
        List<Empleado> listaEmpleados = empleadoService.findAll();
        modelo.addAttribute("listaEmpleados", listaEmpleados);
        return "crud_empleados";
    }

    @GetMapping("/crear")
    public String mostrarFormularioDeNuevoEmpleado(Model modelo){
        List<Cargo>listaCargos = cargoService.findAll();

        modelo.addAttribute("empleado",new Empleado());
        modelo.addAttribute("listaCargos",listaCargos);

        return "empleado_agregar";
    }


    @PostMapping("/guardar")
    public String guardarEmpleado(Empleado empleado) throws IOException {

        empleadoService.save(empleado);
        return "redirect:/empleado/lista_empleados";
    }


    @GetMapping("/crear/{id}")
    public String crearUsuario(@PathVariable Long id,Model modelo){
        Empleado empleado = new Empleado();
        Optional<Empleado> optionalEmpleado=empleadoService.findbyId(id);
        empleado=optionalEmpleado.get();

        modelo.addAttribute("empleado",empleado);
        modelo.addAttribute("usuario",new Usuario());
        List<Privilegio>listaRoles = rolService.findAll();
        modelo.addAttribute("listaRoles",listaRoles);

        return "crear_usuario";
    }



}
