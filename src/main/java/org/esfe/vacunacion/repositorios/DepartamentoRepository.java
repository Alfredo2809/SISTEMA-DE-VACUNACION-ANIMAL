package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    boolean existsByNombreIgnoreCase(String nombre);


    boolean existsByNombreIgnoreCaseAndIdDepartamentoNot(String nombre, Long idDepartamento);
}