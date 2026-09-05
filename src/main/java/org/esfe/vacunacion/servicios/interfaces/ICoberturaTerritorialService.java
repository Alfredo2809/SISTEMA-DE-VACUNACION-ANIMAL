package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.CoberturaTerritorial;

import java.util.List;

/**
 * Interfaz de servicio para la logica de negocio de CoberturaTerritorial.
 */
public interface ICoberturaTerritorialService {

    List<CoberturaTerritorial> listarTodas();

    CoberturaTerritorial buscarPorId(Long id);

    CoberturaTerritorial guardar(CoberturaTerritorial coberturaTerritorial);

    void eliminar(Long id);

    List<CoberturaTerritorial> listarPorCampana(Long idCampana);

    boolean existeAsignacion(Long idCampana, Long idColonia);
}