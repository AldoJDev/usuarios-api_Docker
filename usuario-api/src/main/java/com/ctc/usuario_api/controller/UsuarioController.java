package com.ctc.usuario_api.controller;

import com.ctc.usuario_api.model.Usuario;
import com.ctc.usuario_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario) {
        try {
            if (usuario.getLogin().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Login é obrigatório");
            }
            if (usuario.getPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Password é obrigatório");
            }
            if (usuario.getLogin().length() > 8) {
                return ResponseEntity.badRequest().body("Login deve ter no máximo 8 caracteres");
            }
            if (usuario.getPassword().length() > 8) {
                return ResponseEntity.badRequest().body("Password deve ter no máximo 8 caracteres");
            }

            Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);

        } catch (RuntimeException e) {
            if (e.getMessage().equals("Usuário já existe")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno");
        }
    }

    @GetMapping("/{login}")
    public ResponseEntity<?> verificarUsuario(@PathVariable String login) {
        try {
            boolean existe = usuarioService.existeUsuario(login);
            if (existe) {
                Usuario usuario = usuarioService.buscarUsuario(login);
                return ResponseEntity.ok().body(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno");
        }
    }


    @GetMapping("/check/{login}")
    public ResponseEntity<?> verificarSeExiste(@PathVariable String login) {
        try {
            boolean existe = usuarioService.existeUsuario(login);
            return ResponseEntity.ok().body("{\"exists\": " + existe + "}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno");
        }
    }
}
