package com.universidad.controller;

import com.universidad.model.Usuario;
import com.universidad.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import com.universidad.security.JwtUtil;

import java.util.Map;
import java.util.HashMap;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;
    private final JwtUtil jwtUtil;

    public UsuarioController(UsuarioService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/registro")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return service.registrar(usuario);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginData) {
        String correo = loginData.get("correo");
        String password = loginData.get("password");

        Usuario user = service.autenticar(correo, password);
        Map<String, String> respuesta = new HashMap<>();

        if (user != null) {
            String token = jwtUtil.generateToken(user.getCorreo(), user.getRol());
            respuesta.put("token", token);
            respuesta.put("rol", user.getRol());
        } else {
            respuesta.put("Error", "Credenciales invalidas");
        }
        return respuesta;
    }
}
