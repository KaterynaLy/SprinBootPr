/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_clinica;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PerfilController {

    @GetMapping("/perfil")
    public String perfil(@RequestParam(name = "admin", required = false) Boolean admin) {
        if (Boolean.TRUE.equals(admin)) {
            return "redirect:/admin_perfil.html";
        } else {
            return "redirect:/paciente_perfil.html";
        }
    }
}
