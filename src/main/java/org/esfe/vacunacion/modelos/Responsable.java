package org.esfe.vacunacion.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "responsable")
public class Responsable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_responsable")
    private Long idResponsable;

    @Column(name = "nombre_completo", nullable = false, length = 150)
    private String nombreCompleto;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "documento_identidad", nullable = false, length = 20)
    private String documentoIdentidad;

    public Responsable() {
    }

    public Long getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Long idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void actualizarTelefono(String nuevoTelefono) {
        this.telefono = nuevoTelefono;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }
}
