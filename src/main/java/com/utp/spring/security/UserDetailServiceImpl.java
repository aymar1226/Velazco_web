package com.utp.spring.security;

import com.utp.spring.models.dto.PersonaUsuarioDTO;
import com.utp.spring.models.entity.Persona;
import com.utp.spring.models.entity.Usuario;
import com.utp.spring.services.IPersonaService;
import com.utp.spring.services.IRolService;
import com.utp.spring.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IPersonaService personaService;
    @Autowired
    private IRolService rolService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        PersonaUsuarioDTO persona= personaService
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email "+email+" no existe."));
        return new UserDetailsImpl(persona);
    }
}
