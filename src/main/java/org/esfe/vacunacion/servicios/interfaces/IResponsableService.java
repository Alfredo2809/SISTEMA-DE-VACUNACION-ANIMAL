package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.Responsable;

import java.util.List;

/**
 * Interfaz de servicio para la logica de negocio de Responsable.
 */
public interface IResponsableService {

    List<Responsable> listarTodos();

    Responsable buscarPorId(Long id);

    Responsable guardar(Responsable responsable);

    void eliminar(Long id);

    boolean existsByDocumentoIdentidad(String documentoIdentidad);
}