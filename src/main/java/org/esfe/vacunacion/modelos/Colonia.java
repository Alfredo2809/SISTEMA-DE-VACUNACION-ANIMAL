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
@Table(name = "colonia")
public class Colonia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idColonia;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCanton", nullable = false)
    private Canton canton;

    // Constructor predeterminado (exigido por JPA)
    public Colonia() {
    }

    // Constructor parametrizado
    public Colonia(Long idColonia, String nombre, Canton canton) {
        this.idColonia = idColonia;
        this.nombre = nombre;
        this.canton = canton;
    }

    // Getters y Setters
    public Long getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Long idColonia) {
        this.idColonia = idColonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Canton getCanton() {
        return canton;
    }

    public void setCanton(Canton canton) {
        this.canton = canton;
    }
}