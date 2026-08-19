package org.esfe.vacunacion.modelos;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartamento;

    @Column(nullable = false, length = 100)
    private String nombre;

<<<<<<< Updated upstream
    // TODO: Descomentar cuando la entidad Municipio exista en developer
    // @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Municipio> municipios = new ArrayList<>();
=======
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Municipio> municipios = new ArrayList<>();
>>>>>>> Stashed changes

    public Departamento() {}

    public Departamento(Long idDepartamento, String nombre) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
    }

    public Long getIdDepartamento() { return idDepartamento; }
    public void setIdDepartamento(Long idDepartamento) { this.idDepartamento = idDepartamento; }

<<<<<<< Updated upstream
    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /* TODO: Descomentar cuando la entidad Municipio exista
    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
    */
=======
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<Municipio> getMunicipios() { return municipios; }
    public void setMunicipios(List<Municipio> municipios) { this.municipios = municipios; }
>>>>>>> Stashed changes
}