package com.universidad.service;

import com.universidad.model.Clase;
import com.universidad.model.Nota;
import com.universidad.model.Usuario;
import com.universidad.repository.ClaseRepository;
import com.universidad.repository.NotaRepository;
import com.universidad.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ClaseRepository claseRepository;

    public NotaService(NotaRepository notaRepository, UsuarioRepository usuarioRepository, ClaseRepository claseRepository) {
        this.notaRepository = notaRepository;
        this.usuarioRepository = usuarioRepository;
        this.claseRepository = claseRepository;
    }

    public Nota crearNota(Long estudianteId, Long claseId, double valor) {
        Usuario estudiante = usuarioRepository.findById(estudianteId).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Clase clase = claseRepository.findById(claseId).orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        Nota nota = new Nota();
        nota.setValor(valor);
        nota.setEstudiante(estudiante);
        nota.setClase(clase);

        return notaRepository.save(nota);
    }

    public List<Nota> obtenerNotasPorEstudiante(Long estudianteId) {
        return notaRepository.findByEstudianteId(estudianteId);
    }
}
