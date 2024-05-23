package com.utp.spring.services;

import com.utp.spring.models.dao.IUsuarioDAO;
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
        usuarioDAO.delete(usuario);

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




}
