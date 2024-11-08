/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   /*Usuario findByEmailAndPassword(String email, String password);
}*/

//Método personalizado con @Query para buscar por email y password
    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password")
    Usuario findUsuario(@Param("email") String email, @Param("password") String password);
}

