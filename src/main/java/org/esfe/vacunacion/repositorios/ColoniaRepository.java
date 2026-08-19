package org.esfe.vacunacion.repositorios;
import org.esfe.vacunacion.modelos.Colonia;
import org.esfe.vacunacion.modelos.Canton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface ColoniaRepository extends JpaRepository<Colonia, Long> {
    Optional<Colonia> findByNombre(String nombre);
    List<Colonia> findByCanton(Canton canton);
    List<Colonia> findByNombreContainingIgnoreCase(String nombre);
    Optional<Colonia> findByNombreAndCanton(String nombre, Canton canton);
}