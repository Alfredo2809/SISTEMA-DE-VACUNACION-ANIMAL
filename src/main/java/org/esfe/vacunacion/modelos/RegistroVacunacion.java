package org.esfe.vacunacion.modelos;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_vacunacion")
public class RegistroVacunacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistro;

    @ManyToOne
    @JoinColumn(name = "idMascota", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "idCampana", nullable = false)
    private CampanaVacunacion campana;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVacunacion estado;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(length = 500)
    private String motivoNoVacunacion;

    @Column(length = 500)
    private String observaciones;

    public RegistroVacunacion() {}

    public Long getIdRegistro() { return idRegistro; }
    public void setIdRegistro(Long idRegistro) { this.idRegistro = idRegistro; }

    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }

    public CampanaVacunacion getCampana() { return campana; }
    public void setCampana(CampanaVacunacion campana) { this.campana = campana; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public EstadoVacunacion getEstado() { return estado; }
    public void setEstado(EstadoVacunacion estado) { this.estado = estado; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getMotivoNoVacunacion() { return motivoNoVacunacion; }
    public void setMotivoNoVacunacion(String motivoNoVacunacion) { this.motivoNoVacunacion = motivoNoVacunacion; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}