package com.utp.spring.services;

import com.utp.spring.models.entity.Empleado;
import com.utp.spring.models.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {
    Optional<Proveedor> findbyId(Long id);
    List<Proveedor> findAll();
    Proveedor save(Proveedor proveedor);
    void delete(Proveedor proveedor);
    Proveedor update(Proveedor proveedor);
}
