package com.utp.spring.controllers;

import com.utp.spring.models.entity.*;
import com.utp.spring.services.ICargoService;
import com.utp.spring.services.IEmpleadoService;
import com.utp.spring.services.IRolService;
import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICargoService cargoService;
    @Autowired
    private IRolService rolService;

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

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id){
        Optional<Empleado> empleado = empleadoService.findbyId(id);
        Usuario user =  empleado.get().getUsuario();
        empleadoService.delete(id);
        usuarioService.delete(user);
        return "redirect:/empleado/lista_empleados";
    }

    @GetMapping("/crear/{id}")
    public String crearUsuario(@PathVariable Long id,Model modelo){
        Empleado empleado = new Empleado();
        Optional<Empleado> optionalEmpleado=empleadoService.findbyId(id);
        empleado=optionalEmpleado.get();

        modelo.addAttribute("empleado",empleado);
        modelo.addAttribute("usuario",new Usuario());
        List<Rol>listaRoles = rolService.findAll();
        modelo.addAttribute("listaRoles",listaRoles);

        return "crear_usuario";
    }

    @PostMapping("/crear_usuario")
    public String guardarUsuario(Usuario usuario,Empleado empleado){

        usuario.setPassword (passwordEncoder.encode(usuario.getPassword()));
        Usuario nuevoUsuario= usuarioService.save(usuario);

        Empleado empleadoExistente = empleadoService.findbyId(empleado.getIdEmpleado())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado")); // Obtener el objeto Empleado de manera segura

        empleadoExistente.setUsuario(nuevoUsuario);
        empleadoService.save(empleadoExistente);

        return "redirect:/empleado/lista_empleados";
    }

}
