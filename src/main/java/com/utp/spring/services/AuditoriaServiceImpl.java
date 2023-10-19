package com.utp.spring.services;

import com.utp.spring.models.dao.IAuditoriaDao;
import com.utp.spring.models.entity.Auditoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriaServiceImpl implements IAuditoriaService{

    @Autowired
    IAuditoriaDao auditoriaDao;

    @Override
    public Auditoria findbyId(Long id) {
        return auditoriaDao.findById(id).orElse(null);
    }

    @Override
    public List<Auditoria> findAll() {
        return auditoriaDao.findAll();
    }

    @Override
    public Auditoria save(Auditoria auditoria) {
        return auditoriaDao.save(auditoria);
    }

    @Override
    public void delete(Long id) {
        auditoriaDao.deleteById(id);
    }


}
