package org.esfe.vacunacion.servicios.interfaces;

import org.esfe.vacunacion.modelos.Vivienda;

import java.util.List;

public interface IViviendaService {

    List<Vivienda> listarTodas();

    Vivienda buscarPorId(Long id);

    Vivienda guardar(Vivienda vivienda);

    void eliminar(Long id);

    List<Vivienda> listarPorColonia(Long idColonia);
}