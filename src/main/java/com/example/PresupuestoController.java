/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/presupuestos")
public class PresupuestoController {

    private final PresupuestoService presupuestoService;

    public PresupuestoController(PresupuestoService presupuestoService) {
        this.presupuestoService = presupuestoService;
    }

    @PostMapping
    public ResponseEntity<Presupuesto> crearPresupuesto(@RequestBody Presupuesto presupuesto) {
        Presupuesto nuevoPresupuesto = presupuestoService.crearPresupuesto(presupuesto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPresupuesto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presupuesto> obtenerPresupuesto(@PathVariable Long id) {
        Presupuesto presupuesto = presupuestoService.obtenerPresupuesto(id);
        return ResponseEntity.ok(presupuesto);
    }

    @GetMapping("/listar")
    public List<Presupuesto> listarPresupuestos() {
        return presupuestoService.listarPresupuestos();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Presupuesto> actualizarPresupuesto(@PathVariable Long id, @RequestBody Presupuesto presupuesto) {
        Presupuesto presupuestoActualizado = presupuestoService.actualizarPresupuesto(id, presupuesto);
        return ResponseEntity.ok(presupuestoActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPresupuesto(@PathVariable Long id) {
        presupuestoService.eliminarPresupuesto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}