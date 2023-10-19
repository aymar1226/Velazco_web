package com.utp.spring.services;

import com.utp.spring.models.entity.Auditoria;
import com.utp.spring.models.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IAuditoriaService {

    Auditoria findbyId(Long id);
    List<Auditoria> findAll();
    Auditoria save(Auditoria auditoria);
    void delete(Long id);

}
