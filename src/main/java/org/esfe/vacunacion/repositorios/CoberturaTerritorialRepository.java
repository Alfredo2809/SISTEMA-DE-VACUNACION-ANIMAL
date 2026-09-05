package org.esfe.vacunacion.repositorios;

import org.esfe.vacunacion.modelos.CoberturaTerritorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CoberturaTerritorialRepository extends JpaRepository<CoberturaTerritorial, Long> {

    @Query("SELECT c FROM CoberturaTerritorial c WHERE c.campana.idCampana = :idCampana")
    List<CoberturaTerritorial> listarPorCampana(@Param("idCampana") Long idCampana);

    boolean existsByCampana_IdCampanaAndColonia_IdColonia(Long idCampana, Long idColonia);
}