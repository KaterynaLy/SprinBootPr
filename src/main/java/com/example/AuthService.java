/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

     @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario autenticarUsuario(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmailAndPassword(email, password);
        if (usuario != null) {
            return usuario;
        } else {
            throw new RuntimeException("Credenciales incorrectas");
        }
    }
}


