/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import java.util.List;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*@RestController
@RequestMapping("/auth")
public class AuthController {

        @Autowired
        private AuthService authService;

        @PostMapping("/login")
        public ResponseEntity<UsuarioDTO> login(@RequestBody Usuario usuarioRequest) {
            // Llamar al servicio para autenticar al usuario
            try {
                UsuarioDTO usuarioDTO = authService.autenticarUsuario(usuarioRequest.getEmail(), usuarioRequest.getPassword());

                if (usuarioDTO != null) {
                    return ResponseEntity.ok(usuarioDTO); // Devuelve el usuario con sus datos y rol
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 si las credenciales son incorrectas
                }
            } catch (AuthenticationException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        }
    }*/
