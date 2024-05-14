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

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IRolService rolService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario=usuarioService
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email "+email+" no existe."));
        return new UserDetailsImpl(usuario);
    }
}
