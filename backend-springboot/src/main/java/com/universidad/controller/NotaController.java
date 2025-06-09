package com.universidad.controller;

import com.universidad.model.Nota;
import com.universidad.service.NotaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notas")
@CrossOrigin(origins = "*")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public Nota crearNota(@RequestBody Map<String, Object> body) {
        Long estudianteId = Long.valueOf(body.get("estudianteId").toString());
        Long claseId = Long.valueOf(body.get("claseId").toString());
        double valor = Double.parseDouble(body.get("valor").toString());

        return notaService.crearNota(estudianteId, claseId, valor);
    }

    @GetMapping("/estudiante/{id}")
    public List<Nota> getNotasPorEstudiante(@PathVariable Long id) {
        return notaService.obtenerNotasPorEstudiante(id);
    }
}
