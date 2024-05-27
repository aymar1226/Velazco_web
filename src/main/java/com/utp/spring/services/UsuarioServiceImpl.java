package com.utp.spring.services;

import com.utp.spring.models.dao.IEmpleadoDAO;
import com.utp.spring.models.dao.IPersonaDao;
import com.utp.spring.models.dao.IPrivilegioDAO;
import com.utp.spring.models.dao.IUsuarioDAO;
import com.utp.spring.models.dto.PersonaUsuarioDTO;
import com.utp.spring.models.entity.Empleado;
import com.utp.spring.models.entity.Persona;
import com.utp.spring.models.entity.Privilegio;
import com.utp.spring.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Autowired
    private IPrivilegioDAO privilegioDAO;
    @Autowired
    private IPersonaDao personaDao;
    @Autowired
    private IEmpleadoDAO empleadoDAO;

    @Override
    public Optional<Usuario> findbyId(Long id) {
        return usuarioDAO.findById(id);
    }

    @Override
    public List<Usuario> findAll() {

        return usuarioDAO.findAll();
    }

    @Override
    public Usuario save(Usuario usuario)
    {
        return usuarioDAO.save(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        usuario.setEstado('0');
        Persona personaAEliminar = usuario.getPersona();
        Empleado empleado = usuario.getPersona().getEmpleado();
        System.out.println(personaAEliminar);
        usuario.setPersona(personaAEliminar);
        empleado.setPersona(personaAEliminar);
        empleadoDAO.save(empleado);
        personaDao.save(personaAEliminar);
        usuarioDAO.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String correo) {
        return usuarioDAO.findByEmail(correo);
    }

    @Override
    public String obtenerRolPorId(Long usuarioId) {
        Usuario usuario = usuarioDAO.findById(usuarioId).orElse(null);
        if(usuario!=null){
            Privilegio privilegio = usuario.getPrivilegio();
            if (privilegio !=null){
                return privilegio.getNombre();
            }
        }
        return null;
    }

    @Override
    public Optional<Privilegio> findPrivilegioByEmail(String correo) {
        return privilegioDAO.findByEmail(correo);
    }

    @Override
    public Usuario cambiarPrivilegio(PersonaUsuarioDTO personaUsuarioDTO) {
        Optional<Usuario> usuarioObtenido=usuarioDAO.findByEmail(personaUsuarioDTO.getUsuarioCorreo());
        if(usuarioObtenido.isPresent()){
            usuarioObtenido.get().setPrivilegio(privilegioDAO.findByNombre(personaUsuarioDTO.getUsuarioPrivilegio()).get());

            return usuarioDAO.save(usuarioObtenido.get());
        }
        throw new RuntimeException("El usuario con el correo "+personaUsuarioDTO.getUsuarioCorreo()+" no existe");

    }


}
