package com.utp.spring.services;

import com.utp.spring.models.dao.IEmpleadoDAO;
import com.utp.spring.models.dao.IPersonaDao;
import com.utp.spring.models.dao.IUsuarioDAO;
import com.utp.spring.models.entity.Empleado;
import com.utp.spring.models.entity.Persona;
import com.utp.spring.models.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private IEmpleadoDAO empleadoDAO;
    @Autowired
    private IPersonaDao personaDao;

    @Autowired
    private IUsuarioDAO usuarioDAO;

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

        System.out.println(empleado);

        return empleadoDAO.save(empleado);
    }

    @Override
    public void delete(Long id) {
        empleadoDAO.deleteById(id);
    }

    @Override
    public Usuario crearUsuarioAEmpleado(Persona persona) {

        Optional<Usuario> usuarioExistente = usuarioDAO.findByPersona(persona.getId());

        if(usuarioExistente.isEmpty()){

            Usuario usuario= new Usuario();
            usuario.setPersona(persona);

            return usuarioDAO.save(usuario);
        }

        throw new RuntimeException("La persona ya tiene un usuario asociado");
    }

}
