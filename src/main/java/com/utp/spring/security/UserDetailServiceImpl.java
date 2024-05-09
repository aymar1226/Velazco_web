package com.utp.spring.security;

import com.utp.spring.models.entity.Rol;
import com.utp.spring.models.entity.Usuario;
import com.utp.spring.services.IRolService;
import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IRolService rolService;
    @Autowired
    private BCryptPasswordEncoder bcrypt;
    @Autowired
    HttpSession session;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUser=usuarioService.findByEmail(username);
        if(optionalUser.isPresent()){

            session.setAttribute("idusuario",optionalUser.get().getIdusuario());
            Usuario usuario= optionalUser.get();
            Optional<Rol> rol = rolService.findbyId(usuario.getRol().getIdRol());

            if ( rol== null) {
                throw new UsernameNotFoundException("Rol no encontrado para el usuario");
            }
            return User.builder().username(usuario.getCorreo())
                    .password(usuario.getPassword()).roles(usuario.getRol().getNombre()).build();
        }else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
