/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    private String nombre;
    private String apellido;
    private String documentoIdentidad;
    private Date fechaNacimiento;
    private String email;
    private String telefono;
    private String origenCliente;  // Ej.: convenios, redes, Groupon

    @OneToMany(mappedBy = "paciente")
    private List<Tratamiento> tratamientos;

    // Constructor, getters y setters
}