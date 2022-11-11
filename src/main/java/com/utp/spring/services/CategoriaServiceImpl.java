package com.utp.spring.services;

import com.utp.spring.models.dao.ICategoriaDAO;
import com.utp.spring.models.entity.Categoria;
import com.utp.spring.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService{

    @Autowired
    ICategoriaDAO categoriaDAO;

    @Override
    public List<Categoria> findAll() {
        return categoriaDAO.findAll();
    }

    @Override
    public Categoria save(Categoria categoria) {
        categoriaDAO.save(categoria);
        return categoria;
    }

    @Override
    public Categoria findbyId(Long id) {
        return categoriaDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Categoria categoria) {
        categoriaDAO.delete(categoria);
    }
}
