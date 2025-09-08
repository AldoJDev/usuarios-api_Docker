package com.ctc.usuario_api.repository;

import com.ctc.usuario_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    boolean existsByLogin(String login);
}