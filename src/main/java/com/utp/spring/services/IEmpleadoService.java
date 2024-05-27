package com.utp.spring.services;


import com.utp.spring.models.dto.PersonaUsuarioDTO;
import com.utp.spring.models.entity.Empleado;
import com.utp.spring.models.entity.Persona;
import com.utp.spring.models.entity.Usuario;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoService {

    Optional<Empleado> findbyId(Long id);
    List<Empleado> findAll();
    @Transactional
    Empleado save(Empleado empleado);
    void delete(Empleado empleado);
    @Transactional
    Usuario crearUsuarioAEmpleado(PersonaUsuarioDTO persona);
}
