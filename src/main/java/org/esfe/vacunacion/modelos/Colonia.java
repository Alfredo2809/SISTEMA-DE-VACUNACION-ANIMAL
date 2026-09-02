package org.esfe.vacunacion.modelos;
import jakarta.persistence.*;

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

    public Colonia() {}

    public Colonia(Long idColonia, String nombre, Canton canton) {
        this.idColonia = idColonia;
        this.nombre = nombre;
        this.canton = canton;
    }

    public Long getIdColonia() { return idColonia; }
    public void setIdColonia(Long idColonia) { this.idColonia = idColonia; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Canton getCanton() { return canton; }
    public void setCanton(Canton canton) { this.canton = canton; }
}