package com.universidad.service;

import com.universidad.model.Usuario;
import com.universidad.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario registrar(Usuario usuario) {
        System.out.println("Contraseña recibida: " + usuario.getPassword());

        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new RuntimeException("La contraseña es obligatoria.");
        }

        if (repo.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya esta registrado.");
        }
        return repo.save(usuario);
    }

    public Usuario autenticar(String correo, String contraseña) {
        return repo.findByCorreo(correo)
                .filter(u -> u.getPassword() != null && u.getPassword().equals(contraseña))
                .orElse(null);
    }
}
