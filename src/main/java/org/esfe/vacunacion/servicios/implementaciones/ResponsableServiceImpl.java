package org.esfe.vacunacion.servicios.implementaciones;

import org.esfe.vacunacion.modelos.Responsable;
import org.esfe.vacunacion.repositorios.ResponsableRepository;
import org.esfe.vacunacion.servicios.interfaces.IResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsableServiceImpl implements IResponsableService {

    @Autowired
    private ResponsableRepository responsableRepository;

    @Override
    public List<Responsable> listarTodos() {
        return responsableRepository.findAll();
    }

    @Override
    public Responsable buscarPorId(Long id) {
        return responsableRepository.findById(id).orElse(null);
    }

    @Override
    public Responsable guardar(Responsable responsable) {
        return responsableRepository.save(responsable);
    }

    @Override
    public void eliminar(Long id) {
        responsableRepository.deleteById(id);
    }

    @Override
    public boolean existsByDocumentoIdentidad(String documentoIdentidad) {
        return responsableRepository.existsByDocumentoIdentidad(documentoIdentidad);
    }
}