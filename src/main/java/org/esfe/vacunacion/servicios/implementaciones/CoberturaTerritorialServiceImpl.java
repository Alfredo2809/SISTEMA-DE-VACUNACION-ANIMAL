package org.esfe.vacunacion.servicios.implementaciones;

import org.esfe.vacunacion.modelos.CoberturaTerritorial;
import org.esfe.vacunacion.repositorios.CoberturaTerritorialRepository;
import org.esfe.vacunacion.servicios.interfaces.ICoberturaTerritorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoberturaTerritorialServiceImpl implements ICoberturaTerritorialService {

    @Autowired
    private CoberturaTerritorialRepository coberturaTerritorialRepository;

    @Override
    public List<CoberturaTerritorial> listarTodas() {
        return coberturaTerritorialRepository.findAll();
    }

    @Override
    public CoberturaTerritorial buscarPorId(Long id) {
        return coberturaTerritorialRepository.findById(id).orElse(null);
    }

    @Override
    public CoberturaTerritorial guardar(CoberturaTerritorial coberturaTerritorial) {
        return coberturaTerritorialRepository.save(coberturaTerritorial);
    }

    @Override
    public void eliminar(Long id) {
        coberturaTerritorialRepository.deleteById(id);
    }

    @Override
    public List<CoberturaTerritorial> listarPorCampana(Long idCampana) {
        return coberturaTerritorialRepository.listarPorCampana(idCampana);
    }
}