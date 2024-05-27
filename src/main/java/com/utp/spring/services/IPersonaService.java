package com.utp.spring.services;

import com.utp.spring.models.dto.PersonaUsuarioDTO;
import com.utp.spring.models.dto.RegistroDTO;
import com.utp.spring.models.entity.Cliente;
import com.utp.spring.models.entity.Persona;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    public List<Persona> findAll();
    @Transactional
    public Persona save(RegistroDTO registroDTO);
    public Persona findbyId(Long id);
    public void delete(Persona persona);
    Boolean existsByDNI(String dni);


    Optional<PersonaUsuarioDTO> findByEmail(String correo);

}
