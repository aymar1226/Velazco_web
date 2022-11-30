package com.utp.spring.controllers;

import com.utp.spring.models.entity.Cliente;
import com.utp.spring.models.entity.Producto;
import com.utp.spring.models.entity.Usuario;
import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/perfil")
    public String verPerfil(){
        return "perfil";
    }
    /*@PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario t){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,t.getPassword());
        t.setPassword(hash);
        Usuario o = usuarioService.save(t);
        return new ResponseEntity<Usuario>(o, HttpStatus.OK);
    }

    /*@GetMapping("/buscar/{correo}")
    public ResponseEntity<Usuario> buscarUsuarioPorCorreo(@PathVariable("correo") String correo){

        Usuario o = usuarioService.buscarUsuarioPorCorreo(correo);
        if(o==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(o,HttpStatus.OK);
    }*/

}
