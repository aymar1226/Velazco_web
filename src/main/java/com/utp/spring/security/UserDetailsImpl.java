package com.utp.spring.security;

import com.utp.spring.models.dto.PersonaUsuarioDTO;
import com.utp.spring.models.entity.Persona;
import com.utp.spring.models.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final PersonaUsuarioDTO persona;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(persona.getUsuarioPrivilegio()));
    }

    @Override
    public String getPassword() {
        return persona.getUsuarioContrasenia();
    }

    @Override
    public String getUsername() {
        return persona.getUsuarioCorreo();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre(){
        return persona.getPersonaNombre();
    }
}
