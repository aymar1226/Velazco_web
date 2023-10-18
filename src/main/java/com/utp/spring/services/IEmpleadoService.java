package com.utp.spring.services;


import com.utp.spring.models.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoService {

    Optional<Empleado> findbyId(Long id);
    List<Empleado> findAll();
    Empleado save(Empleado empleado);
    void delete(Long id);
}
