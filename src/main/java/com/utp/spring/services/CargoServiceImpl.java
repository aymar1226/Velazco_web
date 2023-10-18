package com.utp.spring.services;

import com.utp.spring.models.dao.ICargoDao;
import com.utp.spring.models.dao.IProductoDAO;
import com.utp.spring.models.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoServiceImpl implements ICargoService {

    @Autowired
    private ICargoDao cargoDao;

    @Override
    public List<Cargo> findAll() {
        return cargoDao.findAll();
    }

    @Override
    public Cargo save(Cargo cargo) {
        return cargoDao.save(cargo);
    }

    @Override
    public Cargo findbyId(Long id) {
        return cargoDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        cargoDao.deleteById(id);
    }
}
