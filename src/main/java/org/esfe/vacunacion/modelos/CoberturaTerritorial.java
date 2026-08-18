package org.esfe.vacunacion.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "cobertura_territorial")
public class CoberturaTerritorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cobertura")
    private Long idCobertura;

    @ManyToOne
    @JoinColumn(name = "id_campana", nullable = false)
    private CampanaVacunacion campana;

    @ManyToOne
    @JoinColumn(name = "id_colonia", nullable = false)
    private Colonia colonia;

    // Constructor vacío
    public CoberturaTerritorial() {
    }

    // Getters y Setters
    public Long getIdCobertura() {
        return idCobertura;
    }

    public void setIdCobertura(Long idCobertura) {
        this.idCobertura = idCobertura;
    }

    public CampanaVacunacion getCampana() {
        return campana;
    }

    public void setCampana(CampanaVacunacion campana) {
        this.campana = campana;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }
}