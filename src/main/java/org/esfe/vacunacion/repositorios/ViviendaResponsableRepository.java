package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.ViviendaResponsable;
import org.esfe.vacunacion.modelos.ViviendaResponsableId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViviendaResponsableRepository extends JpaRepository<ViviendaResponsable, ViviendaResponsableId> {

    boolean existsById_IdViviendaAndId_IdResponsable(Long idVivienda, Long idResponsable);
}