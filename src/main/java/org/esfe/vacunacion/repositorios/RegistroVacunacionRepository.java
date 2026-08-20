package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.RegistroVacunacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroVacunacionRepository extends JpaRepository<RegistroVacunacion, Long> {
    // Spring Data JPA provee los métodos CRUD por defecto (save, findById, findAll, etc.)
}
