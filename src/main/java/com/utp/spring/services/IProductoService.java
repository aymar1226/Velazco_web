package com.utp.spring.services;

import com.utp.spring.models.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    public List<Producto> findAll();
    public Producto save(Producto producto);
    public Producto findbyId(Long id);
    public void delete(Long id);
    public Optional<Producto> get(Long id);
    public void update(Producto producto);

    public List<Producto> listAll(String palabraClave);



}
