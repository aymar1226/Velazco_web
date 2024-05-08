package com.utp.spring.services;

import com.utp.spring.models.dao.IPersonaDao;
import com.utp.spring.models.entity.Cliente;
import com.utp.spring.models.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

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
        return personaDao.countByDNI(dni)>0;

    }
}
