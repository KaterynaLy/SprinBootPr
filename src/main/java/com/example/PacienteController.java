/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    /*@GetMapping("/{id}/perfil")
    public Paciente obtenerPerfil(@PathVariable Long id) {
        return pacienteService.obtenerPacientePorId(id);
    }

    @GetMapping("/{id}/documentos")
    public List<Documento> obtenerDocumentos(@PathVariable Long id) {
        return pacienteService.obtenerDocumentosPorPaciente(id);
    }

    @GetMapping("/{id}/presupuestos")
    public List<Presupuesto> obtenerPresupuestos(@PathVariable Long id) {
        return pacienteService.obtenerPresupuestosPorPaciente(id);
    }

    @GetMapping("/{id}/tratamientos")
    public List<Tratamiento> obtenerTratamientos(@PathVariable Long id) {
        return pacienteService.obtenerTratamientosPorPaciente(id);
    }*/
    @GetMapping("/{id}/perfil-completo")
    public ResponseEntity<?> obtenerPerfilCompleto(@PathVariable Long id) {
        try {
            Paciente perfil = pacienteService.obtenerPacientePorId(id);
            List<Documento> documentos = pacienteService.obtenerDocumentosPorPaciente(id);
            List<Presupuesto> presupuestos = pacienteService.obtenerPresupuestosPorPaciente(id);
            List<Tratamiento> tratamientos = pacienteService.obtenerTratamientosPorPaciente(id);

            // Crear un objeto para el perfil completo
            PacientePerfilCompleto perfilCompleto = new PacientePerfilCompleto(perfil, documentos, presupuestos, tratamientos);

            return ResponseEntity.ok(perfilCompleto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cargar el perfil completo del paciente.");
        }
    }

    @RestController
    @RequestMapping("/presupuestos")
    public class PresupuestoController {

        private final PresupuestoService presupuestoService;

        public PresupuestoController(PresupuestoService presupuestoService) {
            this.presupuestoService = presupuestoService;
        }

        @GetMapping("/{id}")
        public ResponseEntity<Presupuesto> obtenerPresupuesto(@PathVariable Long id) {
            Presupuesto presupuesto = presupuestoService.obtenerPresupuesto(id);
            return ResponseEntity.ok(presupuesto);
        }
    }
    
     @PostMapping("/add")
    public ResponseEntity<Paciente> addPatient(@RequestBody Paciente paciente) {
        Paciente savedPaciente = pacienteService.addPaciente(paciente);
        return ResponseEntity.ok(savedPaciente);
    }

}
