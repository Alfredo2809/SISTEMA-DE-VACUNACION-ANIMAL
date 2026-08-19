package org.esfe.vacunacion.repositorios;
import org.esfe.vacunacion.modelos.Canton;
import org.esfe.vacunacion.modelos.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface CantonRepository extends JpaRepository<Canton, Long> {
    Optional<Canton> findByNombre(String nombre);
    List<Canton> findByMunicipio(Municipio municipio);
    List<Canton> findByNombreContainingIgnoreCase(String nombre);
    Optional<Canton> findByNombreAndMunicipio(String nombre, Municipio municipio);
}