package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.Colonia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColoniaRepository extends JpaRepository<Colonia, Long> {

    boolean existsByNombreIgnoreCaseAndCantonIdCanton(String nombre, Long idCanton);

    boolean existsByNombreIgnoreCaseAndCantonIdCantonAndIdColoniaNot(String nombre, Long idCanton, Long idColonia);
}