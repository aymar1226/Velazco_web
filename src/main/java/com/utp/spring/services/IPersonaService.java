package com.utp.spring.services;

import com.utp.spring.models.entity.Cliente;
import com.utp.spring.models.entity.Persona;

import java.util.List;

public interface IPersonaService {
    public List<Persona> findAll();
    public Persona save(Persona persona);
    public Persona findbyId(Long id);
    public void delete(Persona persona);
    Boolean existsByDNI(String dni);

}
