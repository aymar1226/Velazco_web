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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
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

    @PostMapping("/guardar")
    public String guardarUsuario(Usuario usuario,Cliente cliente,Model model){

        Rol rol = rolService.findbyId(2L).orElse(null);
        if(clienteService.existsByDNI(cliente.getDni())){
            model.addAttribute("error", "El DNI ya está registrado.");
            return "registro_usuario";
        }else if (rol!=null){
            usuario.setRol(rol);
            usuario.setPassword (passwordEncoder.encode(usuario.getPassword()));
            Usuario nuevoUsuario= usuarioService.save(usuario);

            System.out.println(" "+usuario);

            if(nuevoUsuario!=null){
                if(cliente!=null){
                    cliente.setUsuario(nuevoUsuario);
                    clienteService.save(cliente);
                }
            }
        }
        System.out.println(" "+cliente);


    return "redirect:/";
    }

    @GetMapping("/login")
    String login(){
        return "index";
    }

    @GetMapping("/acceder")
    public String acceder (Usuario usuario,Cliente cliente, HttpSession session){
        Optional<Usuario> user=usuarioService.findbyId(Long.parseLong(session.getAttribute("idusuario").toString()));
        if (user.isPresent()){
            session.setAttribute("idusuario",user.get().getIdusuario());
            session.setAttribute("rolusuario",user.get().getRol().getNombre());
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

    @GetMapping("editar/{id}")
    public String edit(@PathVariable Long id, Model modelo){

        Optional<Usuario> optionalUsuario=usuarioService.findbyId(id);
        Usuario usuario=optionalUsuario.get();
        Cliente cliente = usuario.getCliente();

        modelo.addAttribute("cliente",cliente);
        modelo.addAttribute("usuario",usuario);
        return "perfil";
    }

    @PostMapping("/update")
    public String update(Long id,Usuario usuario) throws IOException {

        Cliente clienteExistente = new Cliente();
        Usuario usuarioExistente = new Usuario();
        Optional<Usuario> optionalUsuario = usuarioService.findbyId(usuario.getIdusuario());
        usuarioExistente = optionalUsuario.get();

        Cliente cliente = usuario.getCliente();

        clienteExistente= clienteService.findbyId(cliente.getId());
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setDni(cliente.getDni());



        usuarioExistente.setCorreo(usuario.getCorreo());

        clienteService.save(clienteExistente);
        usuarioService.save(usuarioExistente);


        return "redirect:/usuario/editar/" + usuario.getIdusuario();
    }

    @PostMapping("/cambiar_contraseña")
    public String cambiarContraseña(@RequestParam Long id,String rawPassword,Model model,String newPassword,RedirectAttributes redirectAttributes){
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
            model.addAttribute("message", "Contraseña actual incorrecta");
        }

        return "redirect:/usuario/editar/" + id;
    }

}
