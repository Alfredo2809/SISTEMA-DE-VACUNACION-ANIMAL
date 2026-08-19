package org.esfe.vacunacion.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registros_vacunacion")
public class RegistroVacunacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    @Column(length = 255)
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campana_id", nullable = false)
    private CampanaVacunacion campanaVacunacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vivienda_id", nullable = false)
    private Vivienda vivienda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Constructors
    public RegistroVacunacion() {
    }

    public RegistroVacunacion(Long id, LocalDate fechaRegistro, String observaciones,
                              CampanaVacunacion campanaVacunacion, Vivienda vivienda, Usuario usuario) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.observaciones = observaciones;
        this.campanaVacunacion = campanaVacunacion;
        this.vivienda = vivienda;
        this.usuario = usuario;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public CampanaVacunacion getCampanaVacunacion() {
        return campanaVacunacion;
    }

    public void setCampanaVacunacion(CampanaVacunacion campanaVacunacion) {
        this.campanaVacunacion = campanaVacunacion;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
