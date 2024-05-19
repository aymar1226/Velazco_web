package com.utp.spring.services;

import com.utp.spring.models.dao.IPersonaDao;
import com.utp.spring.models.dto.PersonaUsuarioDTO;
import com.utp.spring.models.entity.Cliente;
import com.utp.spring.models.entity.Persona;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PersonaServiceImpl implements IPersonaService {

    private final static Logger LOGGER = Logger.getLogger(PersonaServiceImpl.class.getName());

    @Autowired
    private IPersonaDao personaDao;

    @Override
    public List<Persona> findAll() {
        return personaDao.findAll();
    }

    @Override
    public Persona save(Persona persona) {
        return personaDao.save(persona);
    }

    @Override
    public Persona findbyId(Long id) {
        return personaDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    public Boolean existsByDNI(String dni) {
        return false;

    }

    @Override
    public Optional<PersonaUsuarioDTO> findByEmail(String correo) {
        return personaDao.findByEmail(correo);
    }



}
