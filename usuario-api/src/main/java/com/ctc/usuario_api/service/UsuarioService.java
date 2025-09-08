package com.ctc.usuario_api.service;

import com.ctc.usuario_api.model.Usuario;
import com.ctc.usuario_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public boolean existeUsuario(String login){

        return repository.existsByLogin(login);
    }


    public List<Usuario> getAllUsers(){

        return repository.findAll();
    }

    public Usuario salvarUsuario(Usuario usuario){
        if (existeUsuario(usuario.getLogin())){
            throw new RuntimeException("Usuario ja existe");
        }
        return repository.save(usuario);
    }

    public Usuario buscarUsuario(String login){
        return repository.findById(login).orElse(null);
    }





}
