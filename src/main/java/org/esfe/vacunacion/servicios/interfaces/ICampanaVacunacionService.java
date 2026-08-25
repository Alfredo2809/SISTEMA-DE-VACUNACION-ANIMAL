package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.CampanaVacunacion;

import java.util.List;
import java.util.Optional;

public interface ICampanaVacunacionService {

    List<CampanaVacunacion> listar();

    Optional<CampanaVacunacion> buscarPorId(Long id);

    CampanaVacunacion guardar(CampanaVacunacion campana);

    void eliminar(Long id);
}
