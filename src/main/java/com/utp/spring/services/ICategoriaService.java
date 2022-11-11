package com.utp.spring.services;

import com.utp.spring.models.entity.Categoria;
import com.utp.spring.models.entity.Cliente;

import java.util.List;

public interface ICategoriaService {

    public List<Categoria> findAll();
    public Categoria save(Categoria categoria);
    public Categoria findbyId(Long id);
    public void delete(Categoria categoria);
}
