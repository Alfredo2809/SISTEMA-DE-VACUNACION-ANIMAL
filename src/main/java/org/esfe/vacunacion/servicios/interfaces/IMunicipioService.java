package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.Municipio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMunicipioService {
    Municipio guardar(Municipio municipio);
    List<Municipio> obtenerTodos();
    Page<Municipio> obtenerPaginados(Pageable pageable);
    Optional<Municipio> obtenerPorId(Long id);
    void eliminar(Long id);
    boolean existeDepartamento(Long idDepartamento);
}