package com.utp.spring.services;

import com.utp.spring.models.dao.IClienteDAO;
import com.utp.spring.models.dao.IPersonaDao;
import com.utp.spring.models.dao.IPrivilegioDAO;
import com.utp.spring.models.dao.IUsuarioDAO;
import com.utp.spring.models.dto.PersonaUsuarioDTO;
import com.utp.spring.models.dto.RegistroDTO;
import com.utp.spring.models.entity.Cliente;
import com.utp.spring.models.entity.Persona;
import com.utp.spring.models.entity.Privilegio;
import com.utp.spring.models.entity.Usuario;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PersonaServiceImpl implements IPersonaService {

    private final static Logger LOGGER = Logger.getLogger(PersonaServiceImpl.class.getName());

    @Autowired
    private IPersonaDao personaDao;

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Autowired
    private IPrivilegioDAO privilegioDAO;

    @Autowired
    private IClienteDAO clienteDAO;

    @Override
    public List<Persona> findAll() {
        return personaDao.findAll();
    }

    @Override
    public Persona save(RegistroDTO registroDTO) {
        //registrando si el usuario ya existe
        Optional<Usuario> usuarioExiste = usuarioDAO.findByEmail(registroDTO.getCorreo());

        if(usuarioExiste.isEmpty()){
            //Guardar persona
            Persona persona= new Persona();
            persona.setNombre(registroDTO.getNombre());
            persona.setAp_paterno(registroDTO.getAp_paterno());
            persona.setAp_materno(registroDTO.getAp_materno());
            persona.setDireccion(registroDTO.getDireccion());
            persona.setDocumento(registroDTO.getDocumento());
            persona.setTelefono(registroDTO.getTelefono());
            persona.setEstado('1');

            Persona personaGuardada = personaDao.save(persona); // Guardar persona


            if(personaGuardada!=null){

                //Guardar usuario
                Usuario usuario= new Usuario();
                usuario.setCorreo(registroDTO.getCorreo());
                usuario.setPassword(new BCryptPasswordEncoder().encode(registroDTO.getPassword()));
                Privilegio privilegio = privilegioDAO.findById(1L).orElse(null);
                usuario.setPrivilegio(privilegio);
                usuario.setEstado('1');
                usuario.setPersona(personaGuardada);
                usuarioDAO.save(usuario);

                //Guardar como Cliente
                Cliente cliente = new Cliente();
                cliente.setPersona(personaGuardada);
                cliente.setEstado('1');
                clienteDAO.save(cliente);
            }
            return personaGuardada;
        }
        throw new RuntimeException("El usuario con el correo "+registroDTO.getCorreo()+" ya existe");
    }

    @Override
    public Persona findbyId(Long id) {
        return personaDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    public Boolean existsByDNI(String dni) {
        return false;

    }

    @Override
    public Optional<PersonaUsuarioDTO> findByEmail(String correo) {
        return personaDao.findByEmail(correo);
    }



}
