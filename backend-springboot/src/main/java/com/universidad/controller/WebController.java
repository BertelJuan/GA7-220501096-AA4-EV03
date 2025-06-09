package com.universidad.controller;

import com.universidad.model.Usuario;
import com.universidad.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    private final UsuarioService usuarioService;

    public WebController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/usuarios/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        usuarioService.registrar(usuario);
        redirectAttributes.addFlashAttribute("exito", "Usuario registrado correctamente");
        return "redirect:/usuarios/registro";
    }
}
