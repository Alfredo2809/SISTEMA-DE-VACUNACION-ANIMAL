package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.ViviendaResponsable;

import java.util.List;

/**
 * Interfaz de servicio para la logica de negocio de ViviendaResponsable.
 */

public interface IViviendaResponsableService {

    List<ViviendaResponsable> listarTodos();

    ViviendaResponsable guardar(ViviendaResponsable viviendaResponsable);

    boolean existeAsociacion(Long idVivienda, Long idResponsable);
}