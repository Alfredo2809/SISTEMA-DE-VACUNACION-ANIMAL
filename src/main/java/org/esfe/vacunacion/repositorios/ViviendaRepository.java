package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ViviendaRepository extends JpaRepository<Vivienda, Long> {

    @Query("SELECT v FROM Vivienda v WHERE v.colonia.idColonia = :idColonia")
    List<Vivienda> listarPorColonia(@Param("idColonia") Long idColonia);

    boolean existsByDireccion(String direccion);
}