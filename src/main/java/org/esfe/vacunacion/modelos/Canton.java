package org.esfe.vacunacion.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "canton")
public class Canton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCanton;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMunicipio", nullable = false)
    private Municipio municipio;

    // Constructor predeterminado (exigido por JPA)
    public Canton() {
    }

    // Constructor parametrizado
    public Canton(Long idCanton, String nombre, Municipio municipio) {
        this.idCanton = idCanton;
        this.nombre = nombre;
        this.municipio = municipio;
    }

    // Getters y Setters
    public Long getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(Long idCanton) {
        this.idCanton = idCanton;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
}