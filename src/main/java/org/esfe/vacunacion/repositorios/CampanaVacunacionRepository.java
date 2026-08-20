package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.CampanaVacunacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanaVacunacionRepository extends JpaRepository<CampanaVacunacion, Long> {

}
