package org.esfe.vacunacion.modelos;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "municipio")
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMunicipio;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDepartamento", nullable = false)
    private Departamento departamento;

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Canton> cantones = new ArrayList<>();

    public Municipio() {}

    public Municipio(Long idMunicipio, String nombre, Departamento departamento) {
        this.idMunicipio = idMunicipio;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public Long getIdMunicipio() { return idMunicipio; }
    public void setIdMunicipio(Long idMunicipio) { this.idMunicipio = idMunicipio; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    public List<Canton> getCantones() { return cantones; }
    public void setCantones(List<Canton> cantones) { this.cantones = cantones; }
}