package com.utp.spring.services;

import com.utp.spring.models.entity.Producto;
import org.springframework.core.io.Resource;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

public interface IProductoService {
    public List<Producto> findAll();
    public Producto save(Producto producto);
    public void delete(Long id);
    public Optional<Producto> findbyId(Long id);
    public void update(Producto producto);

    public List<Producto> listAll(Long categoriaID);

    public Resource loadProductImage(String imageName) throws MalformedURLException;

    public String findProductImageById(Long productoId);



}
