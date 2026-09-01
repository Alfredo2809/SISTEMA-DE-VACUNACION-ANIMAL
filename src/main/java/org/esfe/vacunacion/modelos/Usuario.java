package org.esfe.vacunacion.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

<<<<<<< HEAD
    // Ajustado a length = 150 según el diagrama
=======
<<<<<<< HEAD
    @NotBlank(message = "El nombre completo es requerido")
=======
    // Ajustado a length = 150 según el diagrama
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
>>>>>>> 9967226b59ff5435e15d6296fb8f4837822cd480
    @Column(nullable = false, length = 150)
    private String nombreCompleto;

    @NotBlank(message = "El correo es requerido")
    @Email(message = "El correo debe tener un formato válido")
    @Column(nullable = false, unique = true, length = 100)
    private String correo;

<<<<<<< HEAD
    // Explicitado length = 255 según el diagrama
=======
<<<<<<< HEAD
    @NotBlank(message = "La contraseña es requerida")
=======
    // Explicitado length = 255 según el diagrama
>>>>>>> b5bcebd2db8c4a32f750858648503697716b458d
>>>>>>> 9967226b59ff5435e15d6296fb8f4837822cd480
    @Column(nullable = false, length = 255)
    private String contrasena;

    @NotNull(message = "El rol es requerido")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolUsuario rol;

    @NotNull(message = "El estado es requerido")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoUsuario estado;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

    public Usuario() {}

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public RolUsuario getRol() { return rol; }
    public void setRol(RolUsuario rol) { this.rol = rol; }

    public EstadoUsuario getEstado() { return estado; }
    public void setEstado(EstadoUsuario estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}