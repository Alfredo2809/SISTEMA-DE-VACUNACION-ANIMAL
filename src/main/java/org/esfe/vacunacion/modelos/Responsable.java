package org.esfe.vacunacion.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "responsable")
public class Responsable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_responsable")
    private Long idResponsable;

    @NotBlank(message = "El nombre completo es obligatorio.")
    @Column(name = "nombre_completo", nullable = false, length = 150)
    private String nombreCompleto;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(regexp = "^\\d{4}-?\\d{4}$", message = "El teléfono debe tener un formato válido (ej. 7123-4567).")
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @NotBlank(message = "El documento de identidad es obligatorio.")
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

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }
}