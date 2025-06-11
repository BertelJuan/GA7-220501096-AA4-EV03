package com.universidad.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.List;

import java.io.IOException;

@Component
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String uri = req.getRequestURI();

        if (uri.contains("/api/usuarios/login") ||
                uri.contains("/api/usuarios/registro") ||
                uri.contains("/h2-console")) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = req.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            if (!jwtUtil.isTokenValid(jwt)) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o expirado");
                return;
            }

            String correo = jwtUtil.getUsernameFromToken(jwt);
            String rol = jwtUtil.getRoleFromToken(jwt);
            var auth = new UsernamePasswordAuthenticationToken(correo, null, List.of(() -> "ROLE_ " + rol));
            SecurityContextHolder.getContext().setAuthentication(auth);

        } else if (!req.getRequestURI().contains("/api/usuarios/login") && !req.getRequestURI().contains("/api/usuarios/registro") &&
                !req.getRequestURI().contains("/h2-console")) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Falta token");
            return;
        }

        chain.doFilter(request, response);
    }

}
