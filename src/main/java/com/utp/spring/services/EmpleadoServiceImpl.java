package com.utp.spring.services;

import com.utp.spring.models.dao.IEmpleadoDAO;
import com.utp.spring.models.dao.IPersonaDao;
import com.utp.spring.models.dao.IPrivilegioDAO;
import com.utp.spring.models.dao.IUsuarioDAO;
import com.utp.spring.models.dto.PersonaUsuarioDTO;
import com.utp.spring.models.entity.Empleado;
import com.utp.spring.models.entity.Persona;
import com.utp.spring.models.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private IPrivilegioDAO privilegioDAO;

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
    @Transactional
    public void delete(Empleado empleado) {

        empleado.setEstado('0');

        Persona personaAEliminar = empleado.getPersona();
        Optional<Usuario> optionalUsuario = usuarioDAO.findByPersona(personaAEliminar.getId());

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            System.out.println(usuario);

            // Cambia el estado de persona y usuario a '0'
            personaAEliminar.setEstado('0');
            usuario.setEstado('0');

            // Actualiza las relaciones
            empleado.setPersona(personaAEliminar);
            usuario.setPersona(personaAEliminar);

            // Guarda los cambios en las entidades
            empleadoDAO.save(empleado);
            personaDao.save(personaAEliminar);
            usuarioDAO.save(usuario);
        } else {
            personaAEliminar.setEstado('0');
            empleado.setPersona(personaAEliminar);
            personaDao.save(personaAEliminar);
            empleadoDAO.save(empleado);
        }
    }

    @Override
    public Usuario crearUsuarioAEmpleado(PersonaUsuarioDTO persona) {

        Optional<Usuario> usuarioExistente = usuarioDAO.findByPersona(persona.getPersonaId());

        if(usuarioExistente.isEmpty()){


            Optional<Persona> personaObtenida= personaDao.findById(persona.getPersonaId());
            Usuario usuario= new Usuario();
            usuario.setPersona(personaObtenida.get());
            usuario.setCorreo(persona.getUsuarioCorreo());
            usuario.setPassword(new BCryptPasswordEncoder().encode(persona.getUsuarioContrasenia()));
            usuario.setPrivilegio(privilegioDAO.findByNombre(persona.getUsuarioPrivilegio()).get());
            usuario.setEstado('1');

            return usuarioDAO.save(usuario);
        }

        throw new RuntimeException("La persona ya tiene un usuario asociado");
    }

}
