package com.utp.spring.services;

import com.utp.spring.models.dao.IProveedorDAO;
import com.utp.spring.models.entity.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements IProveedorService{

    @Autowired
    private IProveedorDAO proveedorDAO;

    @Override
    public Optional<Proveedor> findbyId(Long id) {
        return proveedorDAO.findById(id);
    }

    @Override
    public List<Proveedor> findAll() {
        return proveedorDAO.findAll();
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return proveedorDAO.save(proveedor);
    }

    @Override
    public void delete(Long id) {
        proveedorDAO.deleteById(id);
    }
}
