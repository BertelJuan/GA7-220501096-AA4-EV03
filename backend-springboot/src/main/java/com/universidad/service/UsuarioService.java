package com.universidad.service;

import com.universidad.model.Usuario;
import com.universidad.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario registrar(Usuario usuario) {
        if (repo.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya esta registrado.");
        }
        return repo.save(usuario);
    }
}
