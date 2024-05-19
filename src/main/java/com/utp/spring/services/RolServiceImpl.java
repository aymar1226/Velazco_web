package com.utp.spring.services;

import com.utp.spring.models.dao.IRolDao;
import com.utp.spring.models.entity.Privilegio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolDao rolDao;

    @Override
    public Optional<Privilegio> findbyId(Long id) {
        return rolDao.findById(id);
    }

    @Override
    public List<Privilegio> findAll() {
        return rolDao.findAll();
    }

    @Override
    public Privilegio save(Privilegio privilegio) {
        return rolDao.save(privilegio);
    }

    @Override
    public void delete(Privilegio privilegio) {
        rolDao.delete(privilegio);

    }
}
