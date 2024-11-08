/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    /*public Usuario crearUsuario(Usuario usuario) {
        // Lógica para crear un usuario (en este caso no estamos encriptando la contraseña)
        return usuarioRepository.save(usuario);
    }*/
    
     public Usuario autenticarUsuario(String email, String password) {
        return usuarioRepository.findUsuario(email, password);
    }
    

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}

