/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;
    
     public Paciente addPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente obtenerPacientePorId(Long idPaciente) {
        return pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public List<Documento> obtenerDocumentosPorPaciente(Long idPaciente) {
        return documentoRepository.findByPaciente_Id(idPaciente);
    }

    public List<Presupuesto> obtenerPresupuestosPorPaciente(Long idPaciente) {
        return presupuestoRepository.findByPaciente_Id(idPaciente);
    }

    public List<Tratamiento> obtenerTratamientosPorPaciente(Long idPaciente) {
        return tratamientoRepository.findByPaciente_Id(idPaciente);
    }
     
}
