package com.utp.spring.services;

import com.utp.spring.models.dao.IClienteDAO;
import com.utp.spring.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteDAO clienteDAO;

    @Override
    public List<Cliente> findAll() {
        return clienteDAO.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        clienteDAO.save(cliente);
        return cliente;
    }

    @Override
    public Cliente findbyId(Long id) {
        return clienteDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(Cliente cliente) {
        clienteDAO.delete(cliente);
    }
}
