package com.universidad.controller;

import com.universidad.model.Clase;
import com.universidad.service.ClaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clases")
@CrossOrigin(origins = "*")
public class ClaseController {

    private final ClaseService claseService;

    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @PostMapping
    public Clase crearClase(@RequestBody Clase clase) {
        return claseService.crearClase(clase);
    }

    @GetMapping
    public List<Clase> obtenerClase() {
        return claseService.listarClase();
    }
}
