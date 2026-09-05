package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.Canton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CantonRepository extends JpaRepository<Canton, Long> {

    boolean existsByNombreIgnoreCaseAndMunicipioIdMunicipio(String nombre, Long idMunicipio);

    boolean existsByNombreIgnoreCaseAndMunicipioIdMunicipioAndIdCantonNot(String nombre, Long idMunicipio, Long idCanton);
}