package com.utp.spring.services;

import com.utp.spring.models.dao.IProductoDAO;
import com.utp.spring.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {
    @Autowired
    private IProductoDAO IProductoDAO;

    @Override
    public List<Producto> findAll() {
        return IProductoDAO.findAll();
    }

    @Override
    public Producto save(Producto producto) {
        IProductoDAO.save(producto);
        return producto;
    }

    @Override
    public Producto findbyId(Long id) {
        return IProductoDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        IProductoDAO.deleteById(id);
    }

    @Override
    public Optional<Producto> get(Long id) {
        return IProductoDAO.findById(id);
    }

    @Override
    public void update(Producto producto) {
        IProductoDAO.save(producto);
    }

    public List<Producto> listAll(String palabraClave){
        if(palabraClave !=null){
            return IProductoDAO.findAll(palabraClave);
        }
        return IProductoDAO.findAll();
    }
}
