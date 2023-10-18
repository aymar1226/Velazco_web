package com.utp.spring.services;

import com.utp.spring.models.dao.IEmpleadoDAO;
import com.utp.spring.models.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private IEmpleadoDAO empleadoDAO;

    @Override
    public Optional<Empleado> findbyId(Long id) {
        return empleadoDAO.findById(id);
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoDAO.findAll();
    }

    @Override
    public Empleado save(Empleado empleado) {
        return empleadoDAO.save(empleado);
    }

    @Override
    public void delete(Long id) {
        empleadoDAO.deleteById(id);
    }

}
