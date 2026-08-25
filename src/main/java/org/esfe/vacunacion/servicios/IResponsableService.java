package org.esfe.vacunacion.servicios;

import org.esfe.vacunacion.modelos.Responsable;

import java.util.List;

public interface IResponsableService {

    List<Responsable> listarTodos();

    Responsable buscarPorId(Long id);

    Responsable guardar(Responsable responsable);

    void eliminar(Long id);

    boolean existsByDocumentoIdentidad(String documentoIdentidad);
}