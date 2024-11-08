/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Presupuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPresupuesto;
    
    @ManyToOne
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente")
    private Paciente paciente;
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "monto")
    private BigDecimal montoTotal; // Monto total del presupuesto
    
    private String estado; // Pendiente, Aceptado, Rechazado
    @Column(name = "fechaCreacion")
    private LocalDate fechaCreacion; // Fecha en que se generó el presupuesto
     
    @Column(name = "fechaAceptacion")
    private LocalDate fechaAceptacion; // Fecha en que se generó el presupuesto

    @OneToMany(mappedBy = "presupuesto", cascade = CascadeType.ALL)
    private List<Tratamiento> tratamientos; // Tratamientos incluidos en el presupuesto
    
    //id, descripcion, monto, fechaCreacion, fechaAceptacion, idPaciente -SQL

    public Presupuesto() {
    }

    public Presupuesto(Long idPresupuesto, Paciente paciente, String descripcion, BigDecimal montoTotal, String estado, LocalDate fechaCreacion, LocalDate fechaAceptacion, List<Tratamiento> tratamientos) {
        this.idPresupuesto = idPresupuesto;
        this.paciente = paciente;
        this.descripcion = descripcion;
        this.montoTotal = montoTotal;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaAceptacion = fechaAceptacion;
        this.tratamientos = tratamientos;
    }

    public Long getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Long idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaAceptacion() {
        return fechaAceptacion;
    }

    public void setFechaAceptacion(LocalDate fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

    @Override
    public String toString() {
        return "Presupuesto{" + "idPresupuesto=" + idPresupuesto + ", paciente=" + paciente + ", descripcion=" + descripcion + ", montoTotal=" + montoTotal + ", estado=" + estado + ", fechaCreacion=" + fechaCreacion + ", fechaAceptacion=" + fechaAceptacion + ", tratamientos=" + tratamientos + '}';
    }
    
    

}

