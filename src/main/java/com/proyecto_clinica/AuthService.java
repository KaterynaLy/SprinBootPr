/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_clinica;

import com.proyecto_clinica.usuario.UsuarioRepository;
import com.proyecto_clinica.usuario.Usuario;
import com.proyecto_clinica.rol.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    public Usuario autenticarUsuario(String email, String password) throws AuthenticationException {
        Usuario usuario = usuarioRepository.findByEmailAndPassword(email, password);

        if (usuario == null) {
            throw new AuthenticationException("Credenciales inválidas");
        }

        if (usuario.getRol() == null) {
            throw new AuthenticationException("Rol hueco");
        }

        var roles = rolRepository.findAll();
        var rol = usuario.getRol();

        if (!roles.contains(rol)) {
            throw new AuthenticationException("Rol inválida");
        }
        return usuario;
    }
}