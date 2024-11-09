/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
    List<Tratamiento> findByPaciente_Id(Long idPaciente);
}