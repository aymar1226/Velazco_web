package com.utp.spring.services;

import com.utp.spring.models.entity.Usuario;
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
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    HttpSession session;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUser=usuarioService.findByEmail(username);
        if(optionalUser.isPresent()){
            session.setAttribute("idusuario",optionalUser.get().getIdusuario());
            Usuario usuario= optionalUser.get();
            return User.builder().username(usuario.getNombre()).password(usuario.getPassword()).roles(usuario.getRol()).build();
        }else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
