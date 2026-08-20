package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsableRepository extends JpaRepository<Responsable, Long> {

    boolean existsByDocumentoIdentidad(String documentoIdentidad);
}