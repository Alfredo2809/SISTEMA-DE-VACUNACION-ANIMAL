package org.esfe.vacunacion.modelos;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "vivienda_responsable")
public class ViviendaResponsable {

    @EmbeddedId
    private ViviendaResponsableId id;

    @ManyToOne
    @MapsId("idVivienda")
    @JoinColumn(name = "id_vivienda", nullable = false)
    private Vivienda vivienda;

    @ManyToOne
    @MapsId("idResponsable")
    @JoinColumn(name = "id_responsable", nullable = false)
    private Responsable responsable;

    public ViviendaResponsable() {
    }

    public ViviendaResponsableId getId() {
        return id;
    }

    public void setId(ViviendaResponsableId id) {
        this.id = id;
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