package org.esfe.vacunacion.servicios.implementaciones;

import org.esfe.vacunacion.modelos.Vivienda;
import org.esfe.vacunacion.repositorios.ViviendaRepository;
import org.esfe.vacunacion.servicios.interfaces.IViviendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementacion del servicio de Vivienda.
 */
@Service
public class ViviendaServiceImpl implements IViviendaService {

    @Autowired
    private ViviendaRepository viviendaRepository;

    @Override
    public List<Vivienda> listarTodas() {
        return viviendaRepository.findAll();
    }

    @Override
    public Vivienda buscarPorId(Long id) {
        return viviendaRepository.findById(id).orElse(null);
    }

    @Override
    public Vivienda guardar(Vivienda vivienda) {
        return viviendaRepository.save(vivienda);
    }

    @Override
    public void eliminar(Long id) {
        viviendaRepository.deleteById(id);
    }

    @Override
    public List<Vivienda> listarPorColonia(Long idColonia) {
        return viviendaRepository.listarPorColonia(idColonia);
    }

    @Override
    public boolean existsByDireccion(String direccion) {
        return viviendaRepository.existsByDireccion(direccion);
    }
}