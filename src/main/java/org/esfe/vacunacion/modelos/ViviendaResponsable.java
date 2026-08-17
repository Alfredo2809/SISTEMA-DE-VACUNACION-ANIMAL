package org.esfe.vacunacion.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vivienda_responsable")
public class ViviendaResponsable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vivienda_responsable")
    private Long idViviendaResponsable;

    @ManyToOne
    @JoinColumn(name = "id_vivienda", nullable = false)
    private Vivienda vivienda;

    @ManyToOne
    @JoinColumn(name = "id_responsable", nullable = false)
    private Responsable responsable;

    public ViviendaResponsable() {
    }

    public Long getIdViviendaResponsable() {
        return idViviendaResponsable;
    }

    public void setIdViviendaResponsable(Long idViviendaResponsable) {
        this.idViviendaResponsable = idViviendaResponsable;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
}