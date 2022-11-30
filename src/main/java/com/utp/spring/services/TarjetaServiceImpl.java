package com.utp.spring.services;

import com.utp.spring.models.dao.ITarjetaDAO;
import com.utp.spring.models.dao.IUsuarioDAO;
import com.utp.spring.models.entity.Tarjeta;
import com.utp.spring.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaServiceImpl implements ITarjetaService{
    @Autowired
    private ITarjetaDAO tarjetaDAO;

    @Override
    public Optional<Tarjeta> findbyId(Integer id) {
        return tarjetaDAO.findById(id);
    }

    @Override
    public List<Tarjeta> findAll() {
        return tarjetaDAO.findAll();
    }

    @Override
    public Tarjeta save(Tarjeta tarjeta) {
        return tarjetaDAO.save(tarjeta);
    }

    @Override
    public void delete(Tarjeta tarjeta) {
        tarjetaDAO.delete(tarjeta);

    }
}
