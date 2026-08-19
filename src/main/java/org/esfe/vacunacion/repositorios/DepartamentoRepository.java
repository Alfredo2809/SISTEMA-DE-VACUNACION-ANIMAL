package org.esfe.vacunacion.repositorios;
import org.esfe.vacunacion.modelos.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Optional<Departamento> findByNombre(String nombre);
    List<Departamento> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByNombre(String nombre);
}
