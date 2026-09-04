package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.Canton;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICantonService {
    Canton guardar(Canton canton);
    List<Canton> obtenerTodos();
    Page<Canton> obtenerPaginados(Pageable pageable);
    Optional<Canton> obtenerPorId(Long id);
    void eliminar(Long id);
    boolean existeMunicipio(Long idMunicipio);
}