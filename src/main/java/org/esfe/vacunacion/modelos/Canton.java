package org.esfe.vacunacion.modelos;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "canton", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Colonia> colonias = new ArrayList<>();

    public Canton() {}

    public Canton(Long idCanton, String nombre, Municipio municipio) {
        this.idCanton = idCanton;
        this.nombre = nombre;
        this.municipio = municipio;
    }

    public Long getIdCanton() { return idCanton; }
    public void setIdCanton(Long idCanton) { this.idCanton = idCanton; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Municipio getMunicipio() { return municipio; }
    public void setMunicipio(Municipio municipio) { this.municipio = municipio; }

    public List<Colonia> getColonias() { return colonias; }
    public void setColonias(List<Colonia> colonias) { this.colonias = colonias; }
}