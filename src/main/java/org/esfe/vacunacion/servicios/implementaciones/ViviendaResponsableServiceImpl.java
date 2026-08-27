package org.esfe.vacunacion.servicios.implementaciones;

import org.esfe.vacunacion.modelos.ViviendaResponsable;
import org.esfe.vacunacion.repositorios.ViviendaResponsableRepository;
import org.esfe.vacunacion.servicios.interfaces.IViviendaResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Implementacion del servicio de ViviendaResponsable.
 */

@Service
public class ViviendaResponsableServiceImpl implements IViviendaResponsableService {

    @Autowired
    private ViviendaResponsableRepository viviendaResponsableRepository;

    @Override
    public List<ViviendaResponsable> listarTodos() {
        return viviendaResponsableRepository.findAll();
    }

    @Override
    public ViviendaResponsable guardar(ViviendaResponsable viviendaResponsable) {
        return viviendaResponsableRepository.save(viviendaResponsable);
    }

    @Override
    public boolean existeAsociacion(Long idVivienda, Long idResponsable) {
        return viviendaResponsableRepository.existsById_IdViviendaAndId_IdResponsable(idVivienda, idResponsable);
    }
}