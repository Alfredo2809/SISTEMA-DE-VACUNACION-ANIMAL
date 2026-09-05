package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IDepartamentoService {
    Departamento guardar(Departamento departamento);
    List<Departamento> obtenerTodos();
    Page<Departamento> obtenerPaginados(Pageable pageable);
    Optional<Departamento> obtenerPorId(Long id);
    void eliminar(Long id);
}