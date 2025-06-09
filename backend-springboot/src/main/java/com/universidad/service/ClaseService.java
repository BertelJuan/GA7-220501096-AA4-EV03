package com.universidad.service;

import com.universidad.model.Clase;
import com.universidad.model.Usuario;
import com.universidad.repository.UsuarioRepository;
import com.universidad.repository.ClaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaseService {

    private final ClaseRepository claseRepository;
    private final UsuarioRepository usuarioRepository;

    public ClaseService(ClaseRepository claseRepository, UsuarioRepository usuarioRepository) {
        this.claseRepository = claseRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Clase crearClase(Clase clase) {
        Usuario docente = usuarioRepository.findById(clase.getDocente().getId()).orElseThrow(() -> new RuntimeException("Docente no encontrado"));

        if (!"DOCENTE".equalsIgnoreCase(docente.getRol())) {
            throw new RuntimeException("Solo los usuarios con rol DOCENTE pueden crear clases.");
        }

        Optional<Clase> claseExistente = claseRepository.findByNombreAndDocenteId(clase.getNombre(), docente.getId());

        if (claseExistente.isPresent()) {
            throw new RuntimeException("Ya existe una clase con ese nombre para este docente.");
        }

        clase.setDocente(docente);
        return claseRepository.save(clase);
    }

    public List<Clase> listarClase() {
        return claseRepository.findAll();
    }
}
