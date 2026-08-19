package org.esfe.vacunacion.repositorios;
import org.esfe.vacunacion.modelos.Municipio;
import org.esfe.vacunacion.modelos.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
    Optional<Municipio> findByNombre(String nombre);
    List<Municipio> findByDepartamento(Departamento departamento);
    List<Municipio> findByNombreContainingIgnoreCase(String nombre);
    Optional<Municipio> findByNombreAndDepartamento(String nombre, Departamento departamento);
}