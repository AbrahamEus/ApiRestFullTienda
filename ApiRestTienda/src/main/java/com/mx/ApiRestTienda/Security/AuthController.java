package com.mx.ApiRestTienda.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final String claveSecreta = "MI_CLAVE_SECRETA_SUPER_SEGURA_32CHARS!";

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        Map<String, String> respuesta = new HashMap<>();

        if ("admin".equals(username) && "123".equals(password)) {

            List<String> roles = List.of("ADMIN");  // Agrega el rol esperado

            String token = Jwts.builder()
                    .setSubject(username)
                    .claim("roles", roles)  // <--- AGREGA EL CLAIM "roles"
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1 hora
                    .signWith(Keys.hmacShaKeyFor(claveSecreta.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                    .compact();

            respuesta.put("token", token);
        } else {
            respuesta.put("error", "Credenciales inválidas");
        }

        return respuesta;
    }
}
