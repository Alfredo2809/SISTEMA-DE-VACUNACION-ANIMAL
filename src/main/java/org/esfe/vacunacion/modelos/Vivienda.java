package org.esfe.vacunacion.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vivienda")
public class Vivienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vivienda")
    private Long idVivienda;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "referencia_ubicacion", length = 200)
    private String referenciaUbicacion;

    // TODO: agregar relación @ManyToOne hacia Colonia (idColonia)
    // cuando Eduardo suba la clase Colonia.java a developer (VAC-50).
    // Ejemplo esperado:
    // @ManyToOne
    // @JoinColumn(name = "id_colonia")
    // private Colonia colonia;

    public Vivienda() {
    }

    public Long getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(Long idVivienda) {
        this.idVivienda = idVivienda;
    }

    public String getDireccion() {
        return direccion;
    }

    public void actualizarDireccion(String nuevaDireccion) {
        this.direccion = nuevaDireccion;
    }

    public String getReferenciaUbicacion() {
        return referenciaUbicacion;
    }

    public void setReferenciaUbicacion(String referenciaUbicacion) {
        this.referenciaUbicacion = referenciaUbicacion;
    }
}