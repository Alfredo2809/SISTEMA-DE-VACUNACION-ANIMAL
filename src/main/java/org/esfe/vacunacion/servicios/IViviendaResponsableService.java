package org.esfe.vacunacion.servicios;

import org.esfe.vacunacion.modelos.ViviendaResponsable;

import java.util.List;

public interface IViviendaResponsableService {

    List<ViviendaResponsable> listarTodos();

    ViviendaResponsable guardar(ViviendaResponsable viviendaResponsable);

    boolean existeAsociacion(Long idVivienda, Long idResponsable);
}